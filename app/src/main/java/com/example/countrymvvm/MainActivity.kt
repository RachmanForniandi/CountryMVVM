package com.example.countrymvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.countrymvvm.adapter.CountryAdapter
import com.example.countrymvvm.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel:ListViewModel
    private val countryAdapter =CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        listCountry.apply {
            adapter = countryAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this, Observer {
            countries  -> countries?.let {
            listCountry.visibility = View.VISIBLE
            countryAdapter.updateCountries(it)
        }
        })

        viewModel.countryLoadWhenError.observe(this,Observer{
            isError -> isError?.let { tvWhenError.visibility = if(it) View.VISIBLE else View.GONE  }
        })

        viewModel.countryLoadWhenError.observe(this,Observer{
                isLoading -> isLoading?.let { progressView.visibility = if(it) View.VISIBLE else View.GONE

                if (it){
                    tvWhenError.visibility = View.GONE
                    listCountry.visibility = View.GONE
                }
            }
        })
    }
}
