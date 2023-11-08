package com.mitocode.marketcomposeapp.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(
    context: Context,
    workerParameters: WorkerParameters
) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        println("worker")
        return Result.success()
    }
}