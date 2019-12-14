package com.example.countrymvvm.networkUtils

import com.example.countrymvvm.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface APIService {

    @GET("DevTides/countries/master/countriesV2.json")
    fun getCountries(): Single<List<Country>>
}