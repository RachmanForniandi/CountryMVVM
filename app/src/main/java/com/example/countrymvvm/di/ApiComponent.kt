package com.example.countrymvvm.di

import com.example.countrymvvm.networkUtils.NetworkConfig
import com.example.countrymvvm.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: NetworkConfig)

    fun injectViewModel(viewModel: ListViewModel)
}