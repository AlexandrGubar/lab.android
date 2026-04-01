package com.example.lab1_flowers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val orderResult = MutableLiveData<String?>()

    val clearFormEvent = MutableLiveData<Boolean>()
}