package com.dev.bcepedia.bceplacecom.ui.auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.bcepedia.bceplacecom.R

class SignUpFragment : Fragment() {

  private lateinit var viewModel: SignUpViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.sign_up_fragment, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

  }

  companion object {
    fun newInstance() = SignUpFragment()
  }

}