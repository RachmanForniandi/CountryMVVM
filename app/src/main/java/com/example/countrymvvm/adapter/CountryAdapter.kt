package com.example.countrymvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countrymvvm.R
import com.example.countrymvvm.model.Country
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(val countries: ArrayList<Country?>?): RecyclerView.Adapter<CountryAdapter.CountryHolder>() {

    fun updateCountries(newCountries: List<Country>){
        countries?.clear()
        countries?.addAll(newCountries)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country,parent,false)
        return CountryHolder(view)
    }



    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.bind(countries?.get(position))
    }

    override fun getItemCount(): Int {
        return countries?.size ?:0
    }

    class CountryHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        fun bind(data: Country?) {
            itemView.tv_country.text = data?.countryName
        }

    }
}