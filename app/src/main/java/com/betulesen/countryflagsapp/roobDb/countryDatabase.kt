package com.betulesen.countryflagsapp.roobDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.betulesen.countryflagsapp.model.CountryModel


@Database(entities = [CountryModel::class], version = 1)
abstract class countryDatabase : RoomDatabase() {

    abstract fun countryDao(): countryDAO

    companion object {
        @Volatile
        private var instance: countryDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: databaseCreate(context).also {
                instance = it
            }


        }


        private fun databaseCreate(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            countryDatabase::class.java,
            "CountryDatabase"
        ).build()
    }
}