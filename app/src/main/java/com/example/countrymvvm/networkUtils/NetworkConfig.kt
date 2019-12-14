package com.example.countrymvvm.networkUtils

import com.example.countrymvvm.model.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkConfig() {

    private val BASE_URL = "https://raw.githubusercontent.com";
    private val apiService: APIService

    init {
        apiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(APIService::class.java)
    }

    fun getDataOfCountries():Single<List<Country>>{
        return apiService.getCountries()
    }
}