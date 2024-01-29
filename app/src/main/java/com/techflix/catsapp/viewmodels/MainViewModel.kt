package com.techflix.catsapp.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techflix.catsapp.api.CatDataItem
import com.techflix.catsapp.api.CatsService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject
constructor(private val catsService: CatsService) : ViewModel() {
    private val limit = 20
    private val _catsList = MutableStateFlow<List<CatDataItem>>(listOf())
    val catsList: StateFlow<List<CatDataItem>>
        get() = _catsList

    init {
        viewModelScope.launch {
            getCatsList()
        }
    }

    private suspend fun getCatsList() {
        val response = viewModelScope.async(Dispatchers.IO) {
            catsService.getCatsDataList(limit)
        }.await()

        if (response.isSuccessful && response.body().isNullOrEmpty().not()) {
            _catsList.emit(response.body()!!)
        }
    }
}