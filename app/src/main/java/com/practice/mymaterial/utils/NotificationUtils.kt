package com.practice.mymaterial.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.practice.mymaterial.R
import com.practice.mymaterial.egg_time.receiver.SnoozeReceiver
import com.practice.mymaterial.stock_created.MainActivity


// Notification ID.
private const val NOTIFICATION_ID = 0
private const val REQUEST_CODE = 0
private const val FLAGS = 0


//NotificationUtils.kt
/**
 * Builds and delivers a notification.
 *
 * @param messageBody, notification text.
 * @param context, activity context.
 */

fun NotificationManager.sendNotification(messageBody: String, context: Context) {
    // Create the content intent for the notification, which launches
    // this activity
    val contentIntent = Intent(context, MainActivity::class.java)
    val contentPendingIntent = PendingIntent.getActivity(
        context,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val snoozeIntent = Intent(context, SnoozeReceiver::class.java)
    val snoozePendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE, snoozeIntent, FLAGS)
    val eggImage = BitmapFactory.decodeResource(context.resources, R.drawable.cooked_egg)
    val bigPicStyle = NotificationCompat.BigPictureStyle().bigPicture(eggImage).bigLargeIcon(null)

    val builder =
        NotificationCompat.Builder(context, context.getString(R.string.egg_notification_channel_id))
            .setSmallIcon(R.drawable.cooked_egg)
            .setContentTitle(context.getString(R.string.notification_title))
            .setContentText(messageBody)
            .setContentIntent(contentPendingIntent)
            .setAutoCancel(true)
            .setStyle(bigPicStyle)
            .setLargeIcon(eggImage)
            .addAction(
                R.drawable.egg_icon,
                context.getString(R.string.snooze),
                snoozePendingIntent
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
    notify(NOTIFICATION_ID, builder.build())
}

/**
 * Cancels all notifications.
 *
 */
fun NotificationManager.cancelNotifications() {
    cancelAll()
}
