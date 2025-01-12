package com.betulesen.countryflagsapp.roobDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.betulesen.countryflagsapp.model.CountryModel

@Dao
interface countryDAO {

    @Insert
    suspend fun insertAll(vararg country : CountryModel) : List<Long>

    @Query("SELECT * FROM CountryModel")
    suspend fun getAllCountry() : List<CountryModel>

    @Query("SELECT * FROM CountryModel WHERE uuid = :countryId")
    suspend fun getCuntry(countryId : Int) : CountryModel

    @Query("DELETE FROM CountryModel")
    suspend fun deleteAllCountries()
}