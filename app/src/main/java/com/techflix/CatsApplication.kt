package com.techflix

import android.app.Application
import com.techflix.catsapp.api.CatsService
import com.techflix.catsapp.api.RetrofitHelper

class CatsApplication : Application() {
    private lateinit var catsService: CatsService

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        catsService = RetrofitHelper.getInstance().create(CatsService::class.java)
    }

    fun getCatsService(): CatsService {
        if (::catsService.isInitialized.not()) {
            onCreate()
        }
        return catsService
    }
}