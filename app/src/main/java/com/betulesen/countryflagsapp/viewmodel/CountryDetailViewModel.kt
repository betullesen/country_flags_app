package com.betulesen.countryflagsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betulesen.countryflagsapp.model.CountryModel
import com.betulesen.countryflagsapp.roobDb.countryDatabase
import kotlinx.coroutines.launch

class CountryDetailViewModel(application: Application) : AndroidViewModel(application){

    val countryLiveData = MutableLiveData<CountryModel>()

    fun getDataFromRoom(uuid : Int){
        viewModelScope.launch {
            val dao = countryDatabase(getApplication()).countryDao()
            val country = dao.getCuntry(uuid)
            countryLiveData.value = country
        }
        
    }
}