package com.betulesen.countryflagsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.betulesen.countryflagsapp.R
import com.betulesen.countryflagsapp.databinding.FragmentCountryDetailBinding
import com.betulesen.countryflagsapp.databinding.FragmentFeedBinding
import com.betulesen.countryflagsapp.model.CountryModel
import com.betulesen.countryflagsapp.util.downloadFromUrl
import com.betulesen.countryflagsapp.util.placeholderCreate
import com.betulesen.countryflagsapp.viewmodel.CountryDetailViewModel

class CountryDetailFragment : Fragment() {

    private var _binding: FragmentCountryDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : CountryDetailViewModel
    private var countryID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountryDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryID = CountryDetailFragmentArgs.fromBundle(it).countryID
        }

        viewModel = ViewModelProvider(this)[CountryDetailViewModel::class.java]
        viewModel.getDataFromRoom(countryID)

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner){
            binding.countryName.text = it.countryName
            binding.countryCapital.text = it.countryCapital
            binding.countryRegion.text = it.countryRegion
            binding.countryCurrency.text = it.countryCurrency
            binding.countryLanguage.text = it.countryLanguage
            binding.imageViewDetail.downloadFromUrl(it.imageUrl, placeholderCreate(requireContext()))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}