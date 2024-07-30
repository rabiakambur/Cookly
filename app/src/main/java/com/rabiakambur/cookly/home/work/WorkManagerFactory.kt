package com.rabiakambur.cookly.home.work

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters

class WorkManagerFactory : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        if (workerClassName == NotificationWorker::class.java.name) {
            return NotificationWorker(appContext, workerParameters)
        } else {
            throw RuntimeException("unsupported worker class: $workerClassName")
        }
    }
}