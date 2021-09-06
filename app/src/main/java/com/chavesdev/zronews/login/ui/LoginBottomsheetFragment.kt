package com.chavesdev.zronews.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.chavesdev.zronews.R
import com.chavesdev.zronews.common.util.LoadState
import com.chavesdev.zronews.databinding.FragmentLoginBottomsheetBinding
import com.chavesdev.zronews.login.viewmodel.LoginViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.android.viewmodel.ext.android.viewModel


class LoginBottomsheetFragment : BottomSheetDialogFragment() {

    private val loginViewModel: LoginViewModel by viewModel()

    private lateinit var binding: FragmentLoginBottomsheetBinding

    private var navController: NavController? = null

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

        navController = findNavController()

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
                    showNews()
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

        loginViewModel.email.observe(this, {
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

    private fun showNews() {
        navController?.navigate(R.id.action_loginBottomsheetFragment_to_newsHighlightsFragment)
    }
}