package com.example.countrymvvm.networkUtils

import com.example.countrymvvm.di.DaggerApiComponent
import com.example.countrymvvm.model.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NetworkConfig() {

    @Inject
    lateinit var apiService: APIService

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getDataOfCountries():Single<List<Country>>{
        return apiService.getCountries()
    }
}