package com.example.countrymvvm.di

import com.example.countrymvvm.networkUtils.NetworkConfig
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: NetworkConfig)

}