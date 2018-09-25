package com.optcrm.optreporting.app.workers

import motobeans.architecture.customAppComponents.jetpack.SuperWorker

class TempWorker : SuperWorker() {

    override fun doWork(): Result {

        UtilWorkersTaskTemp().execute()

        return Result.SUCCESS
    }
}