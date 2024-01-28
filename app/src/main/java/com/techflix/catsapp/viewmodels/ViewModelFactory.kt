package com.techflix.catsapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.techflix.catsapp.api.CatsService

class ViewModelFactory(private val catsService: CatsService) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(catsService) as T
    }
}