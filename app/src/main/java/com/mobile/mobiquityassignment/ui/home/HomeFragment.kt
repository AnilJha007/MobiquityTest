package com.mobile.mobiquityassignment.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mobile.mobiquityassignment.R
import com.mobile.mobiquityassignment.base.BaseFragment
import com.mobile.mobiquityassignment.service.database.entity.City
import com.mobile.mobiquityassignment.utils.hide
import com.mobile.mobiquityassignment.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var cityAdapter: CityAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun getLayoutResourceId() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        initListener()
        initViewModel()
    }

    private fun setAdapter() {
        cityAdapter = CityAdapter(::cityCallback)
        rvCities.adapter = cityAdapter
    }

    private fun cityCallback(shouldDelete: Boolean, city: City) {
        if (shouldDelete) {
            viewModel.deleteCityById(city.id)
        } else {
            // TODO: not implemented yet
        }
    }

    private fun initListener() {
        fabAddLocation.setOnClickListener {
            findNavController().navigate(R.id.actionHomeToSelectCity)
        }
    }

    private fun initViewModel() {
        viewModel.getAllCities().observe(viewLifecycleOwner, { cities ->
            cities.takeIf { it.size > 0 }?.let {
                cityAdapter.updateData(it)
                rvCities.show()
                groupNoLocation.hide()
            } ?: run {
                rvCities.hide()
                groupNoLocation.show()
            }
        })
    }
}