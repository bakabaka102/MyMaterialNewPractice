package com.practice.mymaterial.egg_time

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.practice.mymaterial.R
import com.practice.mymaterial.databinding.FragmentEggTimerBinding

/**
 * A simple [Fragment] subclass.
 * Use the [EggTimerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EggTimerFragment : Fragment() {

    private val mViewModel: EggTimeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentEggTimerBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_egg_timer, container, false)

//        val mViewModel = ViewModelProvider(this)[EggTimeViewModel::class.java]
//        val mViewModel: EggTimeViewModel by viewModels()
//        binding.eggTimeViewModel = mViewModel
//        binding.lifecycleOwner = this.viewLifecycleOwner



        createChannelNotification(
            getString(R.string.egg_notification_channel_id),
            getString(R.string.egg_notification_channel_name)
        )
        return binding.root
    }

    private fun createChannelNotification(channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply { setShowBadge(true) }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Time for breakfast"

            val notificationManager = requireActivity().getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    companion object {

        fun newInstance() = EggTimerFragment().apply { }
    }
}