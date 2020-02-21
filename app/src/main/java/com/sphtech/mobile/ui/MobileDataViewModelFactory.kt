package com.sphtech.mobile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sphtech.mobile.repository.MobileDataRepository


@Suppress("UNCHECKED_CAST")
class MobileDataViewModelFactory (
    private val repository: MobileDataRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}