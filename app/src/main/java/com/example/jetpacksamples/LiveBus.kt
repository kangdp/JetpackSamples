package com.example.jetpacksamples
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.lang.IllegalArgumentException

/**
 * 不支持粘性事件
 */
class LiveBus {

    private val mutableLiveDataMaps: MutableMap<String,MutableLiveData<Any>> by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
       mutableMapOf<String,MutableLiveData<Any>>()
    }

    object Holder {
        val liveBus = LiveBus()
    }

    companion object {
        val instance = Holder.liveBus
    }

    fun post(tag: String,data:Any?){
        if (tag == "") throw IllegalArgumentException("The tag str is cannot null")
        val mutableLiveData = mutableLiveDataMaps[tag]
        if (mutableLiveData!=null && mutableLiveData.hasObservers()){//不支持粘性事件
            mutableLiveData.postValue(data)
        }
    }

    @JvmOverloads
    fun observer(tag:String,observer: Observer<Any>,lifecycleOwner: LifecycleOwner? = null){
        if (tag == "") throw IllegalArgumentException("The tag str is cannot null")
        var mutableLiveData = mutableLiveDataMaps[tag]
        if (mutableLiveData == null){
            mutableLiveData = MutableLiveData()
            mutableLiveDataMaps[tag] = mutableLiveData
        }
        if (lifecycleOwner == null){
            //表示所有的observer都处于active状态
            mutableLiveData.observeForever(observer)
        }else {
            //observer状态跟随Activity/Fragment的生命周期
            mutableLiveData.observe(lifecycleOwner,observer)
        }
    }

    fun removeObserver(tag: String,observer: Observer<Any>){
        if (tag == "") throw IllegalArgumentException("The tag str is cannot null")
        val mutableLiveData = mutableLiveDataMaps[tag]
        if (mutableLiveData != null){
            Log.d(LiveBus::class.java.simpleName, "removeObserver: 不为 Null")
            mutableLiveData.removeObserver(observer)
            if (!mutableLiveData.hasObservers()){
                mutableLiveDataMaps.remove(tag)
            }
        }
    }
}