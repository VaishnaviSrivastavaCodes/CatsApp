package com.techflix.catsapp.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsService {
    @GET("/v1/images/search")
    suspend fun getCatsDataList(@Query("limit") limit: Int): Response<List<CatDataItem>>
}