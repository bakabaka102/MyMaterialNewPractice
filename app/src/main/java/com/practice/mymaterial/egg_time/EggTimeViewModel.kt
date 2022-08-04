package com.practice.mymaterial.egg_time

import android.app.AlarmManager
import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.os.SystemClock
import androidx.core.app.AlarmManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.mymaterial.R
import com.practice.mymaterial.egg_time.receiver.AlarmReceiver
import com.practice.mymaterial.utils.cancelNotifications
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EggTimeViewModel(val app: Application) : AndroidViewModel(app) {

    private val REQUEST_CODE = 0
    private val TRIGGER_TIME = "TRIGGER_AT"

    private val minute: Long = 60_000L
    private val second: Long = 1_000L

    private val timerLengthOptions: IntArray = app.resources.getIntArray(R.array.minutes_array)
    private val notifyPendingIntent: PendingIntent

    private val alarmManager = app.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private var prefs = app.getSharedPreferences("eggtimernotifications", Context.MODE_PRIVATE)
    private val notifyIntent = Intent(app, AlarmReceiver::class.java)

    private val _timeSelection = MutableLiveData<Int>()
    val timeSelection: LiveData<Int>
        get() = _timeSelection

    private val _elapsedTime: MutableLiveData<Long> = MutableLiveData<Long>()
    val elapsedTime: LiveData<Long>
        get() = _elapsedTime

    private var _alarmOn = MutableLiveData<Boolean>()
    val isAlarmOn: LiveData<Boolean>
        get() = _alarmOn


    private lateinit var timer: CountDownTimer

    init {
        _alarmOn.value = PendingIntent.getBroadcast(
            getApplication(),
//            app,
            REQUEST_CODE,
            notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        ) != null

        notifyPendingIntent = PendingIntent.getBroadcast(
            getApplication(),
//           app,
            REQUEST_CODE,
            notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        //timerLengthOptions = app.resources.getIntArray(R.array.minutes_array)
        //If alarm is not null, resume the timer back for this alarm
        if (_alarmOn.value == true) {
            createTimer()
        }
    }

    private fun createTimer() {
        viewModelScope.launch {
            val triggerTime = loadTimer()
            val timer = object : CountDownTimer(triggerTime, second) {
                override fun onTick(millisUntilFinished: Long) {
                    _elapsedTime.value = triggerTime - SystemClock.elapsedRealtime()
                    //if (_elapsedTime.value!! <= 0) {
                    if (_elapsedTime.value!! <= 0) {
                        resetTimer()
                    }
                }

                override fun onFinish() {
                    resetTimer()
                }
            }
            timer.start()
        }

    }

    /**
     * Turns on or off the alarm
     *
     * @param isChecked, alarm status to be set.
     */
    fun setAlarm(isChecked: Boolean) {
        when (isChecked) {
            true -> timeSelection.value?.let { startTimer(it) }
            false -> cancelNotification()
        }
    }

    /**
     * Sets the desired interval for the alarm
     *
     * @param timerLengthSelection, interval timerLengthSelection value.
     */
    fun setTimeSelected(timerLengthSelection: Int) {
        _timeSelection.value = timerLengthSelection
    }


    /**
     * Creates a new alarm, notification and timer
     */
    private fun startTimer(timerLengthSelection: Int) {
        _alarmOn.value?.let {
            if (!it) {
                _alarmOn.value = true
                val selectInterval = when (timerLengthSelection) {
                    0 -> second * 10 //For testing only
                    else -> timerLengthOptions[timerLengthSelection] * minute
                }
                val triggerTime = SystemClock.elapsedRealtime() + selectInterval
                // TODO: Step 1.15 call cancel notification
                val notificationManager =
                    ContextCompat.getSystemService(
                        app,
                        NotificationManager::class.java
                    ) as NotificationManager
                notificationManager.cancelNotifications()

                AlarmManagerCompat.setExactAndAllowWhileIdle(
                    alarmManager,
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    triggerTime,
                    notifyPendingIntent
                )

                viewModelScope.launch {
                    saveTimer(triggerTime)
                }
            }
        }
    }

    /**
     * Cancels the alarm, notification and resets the timer
     */
    private fun cancelNotification() {
        resetTimer()
        alarmManager.cancel(notifyPendingIntent)
    }


    /**
     * Resets the timer on screen and sets alarm value false
     */
    private fun resetTimer() {
        timer.cancel()
        _elapsedTime.value = 0
        _alarmOn.value = false
    }

    private suspend fun loadTimer(): Long {
        return withContext(Dispatchers.IO) {
            prefs.getLong(TRIGGER_TIME, 0L)
        }
    }

    private suspend fun saveTimer(triggerTime: Long) = withContext(Dispatchers.IO) {
        prefs.edit().putLong(TRIGGER_TIME, triggerTime).apply()
    }
}