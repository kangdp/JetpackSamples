package com.example.jetpacksamples

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(DataViewModel::class.java)

        val nameObserver = Observer<Event>{
            tvName.text = (it.obj as User).userName
        }
        dataViewModel.data.observe(this,nameObserver)
        dataViewModel.data.postValue(Event("key",User("kdp",28)))
    }
}
