package com.example.jetpacksamples
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGo.setOnClickListener {
            startActivity(Intent(this,TestActivity::class.java))
        }

        LiveBus.instance.observer("tag", observer,this)

    }
    private val observer = Observer<Any> {
        Log.d(MainActivity::class.java.simpleName, "onCreate: tag = $it")
        tvName.text = "$it"
    }

    override fun onDestroy() {
        super.onDestroy()
        LiveBus.instance.removeObserver("tag",observer)
    }

}
