package com.example.jetpacksamples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        btnSend.setOnClickListener {
            LiveBus.instance.post("tag","安卓三部曲")
        }
        btnSend2.setOnClickListener {
            LiveBus.instance.post("test","Android 从入门到放弃")
        }
    }
}