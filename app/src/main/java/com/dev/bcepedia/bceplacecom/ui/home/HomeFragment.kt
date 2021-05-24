package com.dev.bcepedia.bceplacecom.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.dev.bcepedia.bceplacecom.R
import com.dev.bcepedia.bceplacecom.databinding.HomeFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

  private lateinit var viewModel: HomeViewModel
  private lateinit var homeFragmentBinding: HomeFragmentBinding
  private lateinit var auth: FirebaseAuth

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    auth = Firebase.auth

    homeFragmentBinding = DataBindingUtil.inflate(
      inflater, R.layout.home_fragment, container, false)

    homeFragmentBinding.homeSignButton.setOnClickListener {
      if(auth.currentUser != null){
        findNavController().navigate(R.id.action_homeFragment_to_profileActivity)
      }
      findNavController().navigate(R.id.action_homeFragment_to_loginActivity)
    }

    return homeFragmentBinding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    homeFragmentBinding.viewModel = viewModel
    // TODO: Use the ViewModel
  }

}