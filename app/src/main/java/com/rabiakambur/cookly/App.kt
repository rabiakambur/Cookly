package com.rabiakambur.cookly

import android.app.Application
import androidx.work.Configuration
import com.rabiakambur.cookly.home.work.WorkManagerFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(WorkManagerFactory())
            .build()

}