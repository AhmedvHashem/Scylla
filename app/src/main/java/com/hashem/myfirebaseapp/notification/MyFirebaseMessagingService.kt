package com.hashem.myfirebaseapp.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.hashem.myfirebaseapp.MainActivity
import com.hashem.myfirebaseapp.R

class MyFirebaseMessagingService : FirebaseMessagingService() {
    private companion object {
        private val TAG: String = MyFirebaseMessagingService::class.java.simpleName
    }

    private val notificationId = 9874561
    private val channelId = "channel_id_dms"
    private val channelName = "Direct Messages"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        try {
            Log.d(TAG, "onMessageReceived messageId ${remoteMessage.messageId}")
            Log.d(TAG, "onMessageReceived messageType ${remoteMessage.messageType}")
            Log.d(TAG, "onMessageReceived notification ${remoteMessage.notification}")
            Log.d(TAG, "onMessageReceived data ${remoteMessage.data}")

            val title = remoteMessage.notification?.title ?: remoteMessage.data["title"] ?: "title"
            val text = remoteMessage.notification?.body ?: remoteMessage.data["text"] ?: "text"

            showNotification(applicationContext, title, text)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showNotification(
        context: Context,
        title: String,
        message: String,
    ) {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )

        val notification = NotificationCompat
            .Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(true)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
            .build()

        val notificationChannel = NotificationChannelCompat
            .Builder(channelId, NotificationManager.IMPORTANCE_MAX)
            .setName(channelName)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), null)
            .setVibrationEnabled(true)
            .build()

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.createNotificationChannel(notificationChannel)
        notificationManager.notify(notificationId, notification)
    }

    /**
     * There are two scenarios when onNewToken is called:
     * 1) When a new token is generated on initial app startup
     * 2) Whenever an existing token is changed
     * Under #2, there are three scenarios when the existing token is changed:
     * A) App is restored to a new device
     * B) User uninstalls/re-installs the app
     * C) User clears app data
     */
    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
//        sendTokenToServer(token)
    }
}