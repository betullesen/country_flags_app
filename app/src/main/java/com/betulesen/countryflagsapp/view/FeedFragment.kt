package com.betulesen.countryflagsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.betulesen.countryflagsapp.adapter.CountryAdapter
import com.betulesen.countryflagsapp.databinding.FragmentFeedBinding
import com.betulesen.countryflagsapp.databinding.FragmentSplashBinding
import com.betulesen.countryflagsapp.viewmodel.FeedViewModel

class FeedFragment : Fragment() {

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())
    private var countryID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
        viewModel.refreshData()

        binding.countryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.countryRecyclerView.adapter = countryAdapter

        arguments?.let {
            countryID = CountryDetailFragmentArgs.fromBundle(it).countryID

        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.countryRecyclerView.visibility =View.GONE
            binding.errorMessage.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE

            viewModel.refreshDataFromAPI()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()


    }

    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner) {
            countryAdapter.updateCountryList(it)
            binding.countryRecyclerView.visibility = View.VISIBLE
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            if(it) {
                binding.errorMessage.visibility = View.VISIBLE
                binding.countryRecyclerView.visibility = View.GONE
            }else{
                binding.errorMessage.visibility = View.GONE
            }
        }

        viewModel.countryLoading.observe(viewLifecycleOwner) {
            if(it) {
                binding.errorMessage.visibility = View.GONE
                binding.countryRecyclerView.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE

            }else{
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}