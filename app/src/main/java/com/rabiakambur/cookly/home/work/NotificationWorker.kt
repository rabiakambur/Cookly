package com.rabiakambur.cookly.home.work

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.rabiakambur.cookly.R
import com.rabiakambur.cookly.main.MainActivity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.TimeUnit

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            sendNotification(NotificationMessages.getRandomNotificationMessage())
            RecipeNotificationScheduler.enqueueWork(applicationContext, 1, TimeUnit.HOURS)
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    private fun sendNotification(message: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = DESCRIPTION
            }
            notificationManager.createNotificationChannel(channel)
        }

        val bigTextStyle = NotificationCompat.BigTextStyle()
            .bigText(message)

        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(NOTIFICATION_TITLE)
            .setContentText(message)
            .setStyle(bigTextStyle)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    companion object {
        const val CHANNEL_ID = "NEW_RECIPE_CHANNEL"
        const val CHANNEL_NAME = "New Recipe Notifications"
        const val NOTIFICATION_TITLE = "Cookly!"
        const val DESCRIPTION = "Channel for new recipe notifications"
        const val NOTIFICATION_ID = 1
    }
}