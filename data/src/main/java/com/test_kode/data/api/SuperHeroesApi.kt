package com.test_kode.data.api

import com.test_kode.data.api.models.SuperHeroesApiResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface SuperHeroesApi {
    @GET("all.json")
    fun getAllHeroes() : Observable<List<SuperHeroesApiResponse>>
}