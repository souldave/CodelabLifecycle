package com.example.davegilbier.codelablifecycle.step5

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel


class SeekBarViewModel : ViewModel() {

    var seekbarValue = MutableLiveData<Int>()
}