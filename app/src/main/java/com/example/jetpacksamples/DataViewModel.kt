package com.example.jetpacksamples
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataViewModel : ViewModel(){
    val data: MutableLiveData<Event> by lazy {
        MutableLiveData<Event>()
    }
}