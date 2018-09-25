package com.optcrm.optreporting.app.workers

import com.example.munishkumarthakur.workmanager_testing.workers.WorkerInterface.WorkerTask
import motobeans.architecture.customAppComponents.jetpack.SuperWorker

class GlobalWorkHandler : SuperWorker() {

    override fun doWork(): Result {

        println("Worker Running - initWorker Order (START) ")

        val arrayOfWorkers = arrayListOf<WorkerTask.worker>(UtilWorkersTaskTemp())

        for (workerTask in arrayOfWorkers) {
            workerTask.execute()
        }

        println("Worker Running - initWorker Order (SUCCESS RETURN) ")
        return Result.SUCCESS
    }
}