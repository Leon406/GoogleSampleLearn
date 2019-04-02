package me.leon.workmanager

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val continuation = WorkManager.getInstance().beginWith(OneTimeWorkRequest.from(MyWorker::class.java))
//        val continuation = WorkManager.getInstance()
//            .beginWith(
//
                OneTimeWorkRequest.Builder(MyWorker::class.java).addTag("my").build()
//            )
//
//        continuation.enqueue()
//        val res = WorkManager.getInstance().getWorkInfosByTag("my").get()[0].outputData.getString("result")
//        Log.d("MainActivity",res)
//获取一个builder
        val request = PeriodicWorkRequest
            .Builder(MyWorker::class.java, 15, TimeUnit.MINUTES)
            .build()
//插入worker队列，并且使用enqueueUniquePeriodicWork方法，防止重复
        WorkManager.getInstance().enqueueUniquePeriodicWork("period", ExistingPeriodicWorkPolicy.KEEP, request)
        WorkManager.getInstance()
            .getWorkInfosForUniqueWorkLiveData("period").observe(this, Observer {
                Log.d("MainActivity", "111111111")
            })

    }
}
