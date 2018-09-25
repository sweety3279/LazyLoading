package com.optcrm.optreporting.app.workers

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class UtilWorkManager {

    companion object {
        private const val MainWorkManagerUniqueNamePeridically = "mainWorkManagerUniqueNamePeridically"
        private const val MainWorkManagerUniqueNameOneTime = "mainWorkManagerUniqueNameOneTime"

        private val mWorkManager = WorkManager.getInstance()
        private val workConstraintsGlobal = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        fun globalWorkManagerPeriodically() {

            mWorkManager?.cancelAllWorkByTag(MainWorkManagerUniqueNamePeridically)

            val mainWork = PeriodicWorkRequestBuilder<GlobalWorkHandler>(1, TimeUnit.HOURS)
                .setConstraints(workConstraintsGlobal)
                .addTag(MainWorkManagerUniqueNamePeridically)
                .build()

            mWorkManager?.enqueue(mainWork)
        }


        fun globalWorkManagerOneTime() {

            mWorkManager?.cancelAllWorkByTag(MainWorkManagerUniqueNameOneTime)

            val mainWork = OneTimeWorkRequestBuilder<GlobalWorkHandler>()
                .setConstraints(workConstraintsGlobal)
                .addTag(MainWorkManagerUniqueNameOneTime)
                .build()

            mWorkManager?.enqueue(mainWork)
        }
    }
}