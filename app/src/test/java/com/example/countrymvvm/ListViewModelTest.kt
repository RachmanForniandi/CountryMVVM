package com.example.countrymvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.countrymvvm.model.Country
import com.example.countrymvvm.networkUtils.NetworkConfig
import com.example.countrymvvm.viewmodel.ListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class ListViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var netConfig: NetworkConfig

    @InjectMocks
    var listViewModel = ListViewModel()

    private var testSingle: Single<List<Country>>?= null

    @Before
    fun setupUnitTest(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getSuccessResult(){
        val country = Country("countryName","capital","url")
        val countriesList = arrayListOf(country)

        testSingle = Single.just(countriesList)

        `when`(netConfig.getDataOfCountries()).thenReturn(testSingle)

        listViewModel.refresh()

        Assert.assertEquals(1,listViewModel.countries.value?.size)
        Assert.assertEquals(false,listViewModel.countryLoadWhenError.value)
        Assert.assertEquals(false,listViewModel.loading.value)


    }

    @Test
    fun getFailedResult(){

        testSingle = Single.error<List<Country>>(Throwable())

        Mockito.`when`(netConfig.getDataOfCountries()).thenReturn(testSingle)

        listViewModel.refresh()

        Assert.assertEquals(true, listViewModel.countryLoadWhenError.getValue())
        Assert.assertEquals(false, listViewModel.loading.getValue())
    }

    @Before
    fun setUpRxSchedulers(){

        val immediate = object : Scheduler(){
            override fun createWorker(): Scheduler.Worker {
                return ExecutorScheduler.ExecutorWorker({ runnable -> runnable.run() }, true)
            }

            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }


        }

        RxJavaPlugins.setInitIoSchedulerHandler { scheduler-> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler-> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler-> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler-> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler-> immediate }
    }
}