package me.leon.workmanager

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * @author : Leon Shih
 * @time   : 2019/3/18 0018 10:51
 * @e-mail : deadogone@gmail.com    :
 * @desc   :
 */
class MyWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {

    @SuppressLint("RestrictedApi")
    override fun doWork(): Result {
       val outputData = Data.Builder()
            .putString("result", "this is result")
            .build()
        return Result.Success(outputData)
    }
}