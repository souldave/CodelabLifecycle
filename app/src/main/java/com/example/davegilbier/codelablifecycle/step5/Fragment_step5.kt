package com.example.davegilbier.codelablifecycle.step5

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.example.davegilbier.codelablifecycle.R
import kotlinx.android.synthetic.main.fragment_step5.view.*


class Fragment_step5 : Fragment() {

    private var mSeekBar: SeekBar? = null

    private var mSeekBarViewModel: SeekBarViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_step5, container, false)
        mSeekBar = root.seekBar

        mSeekBarViewModel = ViewModelProviders.of(this!!.activity!!).get(SeekBarViewModel::class.java)

        subscribeSeekBar()

        return root
    }

    private fun subscribeSeekBar() {

        mSeekBar!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    Log.d("Step5", "Progress changed!")
                    mSeekBarViewModel!!.seekbarValue.setValue(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        mSeekBarViewModel!!.seekbarValue.observe(this!!.activity!!, Observer<Int> { value ->
            if (value != null) {
                mSeekBar!!.progress = value
            }
        })
    }
}
