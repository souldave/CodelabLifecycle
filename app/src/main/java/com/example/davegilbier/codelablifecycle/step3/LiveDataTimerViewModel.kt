package com.example.davegilbier.codelablifecycle.step3

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.SystemClock
import java.util.*

class LiveDataTimerViewModel : ViewModel() {

    private val mElapsedTime = MutableLiveData<Long>()

    private val mInitialTime: Long

    val elapsedTime: LiveData<Long>
        get() = mElapsedTime

    init {
        mInitialTime = SystemClock.elapsedRealtime()
        val timer = Timer()

        // Update the elapsed time every second.
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                mElapsedTime.postValue(newValue)
            }
        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())

    }

    companion object {

        private val ONE_SECOND = 1000
    }
}