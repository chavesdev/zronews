package com.chavesdev.zronews.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.chavesdev.zronews.common.util.LoadState
import com.chavesdev.zronews.databinding.FragmentLoginBottomsheetBinding
import com.chavesdev.zronews.login.viewmodel.LoginViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.android.viewmodel.ext.android.viewModel


class LoginBottomsheetFragment : BottomSheetDialogFragment() {

    private val loginViewModel: LoginViewModel by viewModel()

    private lateinit var binding: FragmentLoginBottomsheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBottomsheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = loginViewModel
        binding.btnSignin.setOnClickListener {
            loginViewModel.tryLogin()
        }

        binding.lifecycleOwner = this
    }

    override fun onResume() {
        super.onResume()
        registerObservables()
    }

    private fun registerObservables() {
        loginViewModel.loginState.observe(this, {
            when (it) {
                LoadState.READY -> {
                    toggleProgress(false)
                    toggleBtnSignin(true)
                }
                is LoadState.SUCCESS -> {
                    toggleProgress(false)
                    toggleBtnSignin(true)
                    //goToHome
                }
                LoadState.LOADING -> {
                    toggleProgress(true)
                    toggleBtnSignin(false)
                }
                is LoadState.ERROR -> {
                    toggleProgress(false)
                    toggleBtnSignin(true)
                    MaterialAlertDialogBuilder(requireActivity()).apply {
                        setMessage(it.msg)
                    }.show()
                }
            }
        })

        loginViewModel.username.observe(this, {
            loginViewModel.checkForm()
        })

        loginViewModel.password.observe(this, {
            loginViewModel.checkForm()
        })
    }

    private fun toggleProgress(show: Boolean) {
        binding.contentProgress.isVisible = show
    }

    private fun toggleBtnSignin(enabled: Boolean) {
        binding.btnSignin.isEnabled = enabled
    }
}