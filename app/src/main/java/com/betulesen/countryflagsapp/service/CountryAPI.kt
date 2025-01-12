package com.betulesen.countryflagsapp.service

import com.betulesen.countryflagsapp.model.CountryModel
import retrofit2.http.GET

interface CountryAPI {
    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    // https://cdn.simplelocalize.io
    ///public/v1/countries

    // https://restcountries.com/v3.1/all

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    suspend fun getCountry() : List<CountryModel>
}