package com.rabiakambur.cookly.home.work

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object RecipeNotificationScheduler {
    fun scheduleRecipeNotification(context: Context) {
        enqueueWork(context, 1, TimeUnit.HOURS)
    }

    fun enqueueWork(context: Context, delay: Long, timeUnit: TimeUnit) {
        val workRequest: OneTimeWorkRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(delay, timeUnit)
            .build()

        WorkManager.getInstance(context).enqueueUniqueWork(
            "RecipeNotificationWork",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
    }
}