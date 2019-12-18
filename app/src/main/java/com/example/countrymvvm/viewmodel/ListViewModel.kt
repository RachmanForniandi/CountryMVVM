package com.example.countrymvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countrymvvm.di.DaggerApiComponent
import com.example.countrymvvm.model.Country
import com.example.countrymvvm.networkUtils.APIService
import com.example.countrymvvm.networkUtils.NetworkConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel:ViewModel() {

    @Inject
    lateinit var networkConfig: NetworkConfig


    init {
        DaggerApiComponent.create().injectViewModel(this)
    }

    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val countryLoadWhenError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        showDataCountry()
    }



    private fun showDataCountry() {
        /*val mockData = listOf(
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
        countries.value = mockData*/
        loading.value = true
        disposable.add(
            networkConfig.getDataOfCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(data: List<Country>) {
                        countries.value = data
                        countryLoadWhenError.value = false
                        loading.value = false

                    }

                    override fun onError(e: Throwable) {
                        countryLoadWhenError.value = true
                        loading.value = false
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}