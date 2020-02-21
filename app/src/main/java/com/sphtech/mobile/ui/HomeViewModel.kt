package com.sphtech.mobile.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sphtech.mobile.data.DataUsageYearly
import com.sphtech.mobile.util.ConverterUtil
import com.sphtech.mobile.util.Coroutines
import com.sphtech.mobile.repository.MobileDataRepository
import kotlinx.coroutines.Job

class HomeViewModel( private val repository: MobileDataRepository) : ViewModel() {

    private lateinit var job : Job

    private val _mobileData = MutableLiveData<List<DataUsageYearly>>()

    val mobileData : LiveData<List<DataUsageYearly>> get() = _mobileData


    fun getMobileData(){
        job = Coroutines.ioToMain(
            {repository.getMobileData("a807b7ab-6cad-4aa6-87d0-e283a7353a0f")},
            {
                _mobileData.value = ConverterUtil.convertMobileDataUsageToYearly(it!!)
            }
        )
    }


    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }

}