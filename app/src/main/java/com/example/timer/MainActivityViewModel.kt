package com.example.timer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel :ViewModel() {

    var samaye:Long =0

  val timeWhenStart : MutableLiveData<Long> by lazy {
      MutableLiveData<Long>()
  }

    val timeWhenPause : MutableLiveData<Long> by lazy {
        MutableLiveData<Long>()
    }

    val timeWhenReset: MutableLiveData<Long> by lazy {
        MutableLiveData<Long>()
    }

}