package com.chavesdev.zronews.register.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.chavesdev.zronews.common.util.LoadState
import com.chavesdev.zronews.databinding.FragmentRegisterBottomSheetBinding
import com.chavesdev.zronews.register.repo.models.ErrorRegisterModel
import com.chavesdev.zronews.register.viewmodel.RegisterViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterBottomSheetFragment : BottomSheetDialogFragment() {

    private val registerViewModel: RegisterViewModel by viewModel()

    private lateinit var binding: FragmentRegisterBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = registerViewModel
        binding.lifecycleOwner = this
        binding.btnSignup.setOnClickListener { registerViewModel.tryRegister() }
    }

    override fun onResume() {
        super.onResume()
        registerObservables()
    }

    private fun registerObservables() {
        registerViewModel.registerState.observe(this, {
            when (it) {
                LoadState.LOADING -> {
                    binding.contentProgress.isVisible = true
                    toggleBtnSignup(false)
                }

                is LoadState.SUCCESS -> {
                    binding.contentProgress.isVisible = false
                    toggleBtnSignup(false)
                    //go To Home
                }

                is LoadState.ERROR -> {
                    binding.contentProgress.isVisible = false
                    toggleBtnSignup(true)
                    handleErrors(it.errors as List<ErrorRegisterModel>)
                }

                LoadState.READY -> {
                    binding.contentProgress.isVisible = false
                    toggleBtnSignup(false)
                }
            }
        })

        registerViewModel.name.observe(this, { registerViewModel.checkForm() })
        registerViewModel.email.observe(this, { registerViewModel.checkForm() })
        registerViewModel.password.observe(this, { registerViewModel.checkForm() })
        registerViewModel.passwordConfirm.observe(this, { registerViewModel.checkForm() })

        registerViewModel.nameError.observe(this, {
            binding.inputName.error = if (it.isNotEmpty()) it else null
        })

        registerViewModel.emailError.observe(this, {
            binding.inputEmail.error = if(it.isNotEmpty()) it else null
        })

        registerViewModel.passwordError.observe(this, {
            binding.inputPassword.error = if(it.isNotEmpty()) it else null
        })

        registerViewModel.passwordConfirmError.observe(this, {
            binding.inputPasswordConfirm.error = if(it.isNotEmpty()) it else null
        })
    }

    private fun toggleBtnSignup(enabled: Boolean) {
        binding.btnSignup.isEnabled = enabled
    }

    private fun handleErrors(errors: List<ErrorRegisterModel>?) {
        errors?.let { list ->
            list.forEach {
                when (it.field) {
                    "password" -> {
                        registerViewModel.passwordError.postValue(it.message)
                    }

                    "name" -> {
                        registerViewModel.nameError.postValue(it.message)
                    }

                    "email" -> {
                        registerViewModel.emailError.postValue(it.message)
                    }
                }
            }
        }
    }
}