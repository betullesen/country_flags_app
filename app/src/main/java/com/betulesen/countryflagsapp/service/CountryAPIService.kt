package com.betulesen.countryflagsapp.service

import com.betulesen.countryflagsapp.model.CountryModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryAPIService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CountryAPI::class.java)

    suspend fun getData() : List<CountryModel> {
        return retrofit.getCountry()
    }
}