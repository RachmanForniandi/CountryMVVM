package com.example.countrymvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countrymvvm.model.Country

class ListViewModel:ViewModel() {

    val countries = MutableLiveData<List<Country>>()
    val countryLoadWhenError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        showDataCountry()
    }

    private fun showDataCountry() {
        val mockData = listOf(
            Country("CountryA"),
            Country("CountryB"),
            Country("CountryC"),
            Country("CountryD"),
            Country("CountryE"),
            Country("CountryF"),
            Country("CountryG"),
            Country("CountryH"),
            Country("CountryI"),
            Country("CountryJ")
        )

        countryLoadWhenError.value = false
        loading.value = false
        countries.value = mockData
    }
}