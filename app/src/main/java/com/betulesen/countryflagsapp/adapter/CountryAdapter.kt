package com.betulesen.countryflagsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.betulesen.countryflagsapp.databinding.RecyclerRowBinding
import com.betulesen.countryflagsapp.model.CountryModel
import com.betulesen.countryflagsapp.util.downloadFromUrl
import com.betulesen.countryflagsapp.util.placeholderCreate
import com.betulesen.countryflagsapp.view.FeedFragmentDirections

class CountryAdapter(val countryList : ArrayList<CountryModel>)  : RecyclerView.Adapter<CountryAdapter.countryViewHolder>() {

    class countryViewHolder(val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): countryViewHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return countryViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return countryList.size
    }

    override fun onBindViewHolder(holder: countryViewHolder, position: Int) {
        holder.binding.countryName.text = countryList[position].countryName
        holder.binding.region.text = countryList[position].countryRegion

        holder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryDetailFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.flagImg.downloadFromUrl(countryList[position].imageUrl, placeholderCreate(holder.itemView.context))
    }

    fun updateCountryList(newCountryList : List<CountryModel>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}