package com.example.davegilbier.codelablifecycle.step3

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.example.davegilbier.codelablifecycle.R
import kotlinx.android.synthetic.main.chrono_activity_3.*

class ChronoActivity3 : AppCompatActivity() {

    private var mLiveDataTimerViewModel: LiveDataTimerViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.chrono_activity_3)

        mLiveDataTimerViewModel = ViewModelProviders.of(this).get(LiveDataTimerViewModel::class.java)

        subscribe()
    }

    private fun subscribe() {
        val elapsedTimeObserver = Observer<Long> { aLong ->
            val newText = this@ChronoActivity3.resources.getString(
                    R.string.seconds, aLong)
            (timer_textview as TextView).text = newText
            Log.d("ChronoActivity3", "Updating timer")
        }

        mLiveDataTimerViewModel!!.getElapsedTime().observe(this, elapsedTimeObserver)
    }
}