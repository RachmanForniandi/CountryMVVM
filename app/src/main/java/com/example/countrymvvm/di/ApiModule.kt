package com.example.countrymvvm.di

import androidx.transition.Visibility
import com.example.countrymvvm.networkUtils.APIService
import com.example.countrymvvm.networkUtils.NetworkConfig
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://raw.githubusercontent.com"

    @Provides
    fun provideApiService(): APIService{

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(APIService::class.java)
    }

    @Provides
    fun provideCountryApiService():NetworkConfig{
        return NetworkConfig()
    }


}