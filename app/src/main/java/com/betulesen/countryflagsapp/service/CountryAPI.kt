package com.betulesen.countryflagsapp.service

import com.betulesen.countryflagsapp.model.CountryModel
import retrofit2.http.GET

interface CountryAPI {
   

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    suspend fun getCountry() : List<CountryModel>
}
