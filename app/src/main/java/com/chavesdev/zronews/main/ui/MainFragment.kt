package com.chavesdev.zronews.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.chavesdev.zronews.R
import com.chavesdev.zronews.common.util.LoadState
import com.chavesdev.zronews.databinding.FragmentMainBinding
import com.chavesdev.zronews.main.viewModel.MainActivityViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private val mainViewModel: MainActivityViewModel by viewModel()

    private var navController: NavController? = null
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.checkAuth()
        registerObservables()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.btnSignin.setOnClickListener { showLogin() }
        binding.btnSignup.setOnClickListener { showRegister() }
    }

    private fun showLogin() {
        navController?.navigate(R.id.action_mainFragment_to_loginBottomsheetFragment)
    }

    private fun showRegister() {
        navController?.navigate(R.id.action_mainFragment_to_registerBottomSheetFragment)
    }

    private fun registerObservables() {
        mainViewModel.authState.observe(this, {
            when (it) {
                is LoadState.SUCCESS -> {
                    it.data?.let {
                        Toast.makeText(requireContext(), "User is logged in", Toast.LENGTH_LONG).show()
                        //navigate to home
                        //navController.navigate()
                    }
                }
            }
        })
    }
}