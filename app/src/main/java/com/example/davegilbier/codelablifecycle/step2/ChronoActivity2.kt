package com.example.davegilbier.codelablifecycle.step2

import android.os.Bundle
import android.os.SystemClock
import android.arch.lifecycle.ViewModelProviders

import android.support.v7.app.AppCompatActivity
import com.example.davegilbier.codelablifecycle.R
import kotlinx.android.synthetic.main.activity_main.*

class ChronoActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chronometerViewModel = ViewModelProviders.of(this).get(ChronometerViewModel::class.java)
        val chronometer = chronometer

        if (chronometerViewModel.getStartTime() == null) {
            val startTime = SystemClock.elapsedRealtime()
            chronometerViewModel.setStartTime(startTime)
            chronometer.setBase(startTime)
        } else {
            chronometer.setBase(chronometerViewModel.getStartTime())
        }

        chronometer.start()
    }
}