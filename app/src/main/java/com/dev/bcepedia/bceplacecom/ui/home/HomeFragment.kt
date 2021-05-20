package com.dev.bcepedia.bceplacecom.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.dev.bcepedia.bceplacecom.R
import com.dev.bcepedia.bceplacecom.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

  private lateinit var viewModel: HomeViewModel
  private lateinit var homeFragmentBinding: HomeFragmentBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    homeFragmentBinding = DataBindingUtil.inflate(
      inflater, R.layout.home_fragment, container, false)



    return homeFragmentBinding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    homeFragmentBinding.viewModel = viewModel
    // TODO: Use the ViewModel
  }

}