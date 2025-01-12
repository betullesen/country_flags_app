package com.betulesen.countryflagsapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betulesen.countryflagsapp.model.CountryModel
import com.betulesen.countryflagsapp.roobDb.countryDatabase
import com.betulesen.countryflagsapp.service.CountryAPIService
import com.betulesen.countryflagsapp.util.PrivateSharedPreferences
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FeedViewModel(application: Application) : AndroidViewModel(application) {

    private val countryAPIService = CountryAPIService()
    private val privateSharedPreferences = PrivateSharedPreferences(getApplication())

    private val currentTime = 10 * 60 *1000 * 1000 * 1000L

    val countries = MutableLiveData<List<CountryModel>>()
    val errorMessage = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        val recordingTime = privateSharedPreferences.timeTake()

        if(recordingTime != null && recordingTime != 0L && System.nanoTime() - recordingTime < currentTime) {
            getDataFromRoom()
        }else{
            getDataFromAPI()
        }
    }
    fun refreshDataFromAPI(){
        getDataFromAPI()
    }

    private fun getDataFromRoom(){
        countryLoading.value = true

        viewModelScope.launch(Dispatchers.IO){
            val countryList = countryDatabase(getApplication()).countryDao().getAllCountry()
            withContext(Dispatchers.Main){
                showCountries(countryList)
                Toast .makeText(getApplication(),"Data taken from room",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getDataFromAPI(){
        countryLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val countryList = countryAPIService.getData()
            withContext(Dispatchers.Main){
                countryLoading.value = false
                saveRoom(countryList)
                Toast.makeText(getApplication(), "Data taken from the API", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showCountries(countryList: List<CountryModel>){
        countries.value = countryList
        countryLoading.value = false
        errorMessage.value = false
    }

    private fun saveRoom(countryLists : List<CountryModel>){
        viewModelScope.launch {

            val dao = countryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val uuidList = dao.insertAll(*countryLists.toTypedArray())
            var i = 0
            while (i < countryLists.size){
                countryLists[i].uuid = uuidList[i].toInt()
                i = i + 1
            }
            showCountries(countryLists)
        }

        privateSharedPreferences.timeSave(System.nanoTime())
    }
}

