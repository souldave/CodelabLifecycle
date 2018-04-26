package com.example.davegilbier.codelablifecycle.step1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.davegilbier.codelablifecycle.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val chronometer = chronometer
        chronometer.start()
    }
}
