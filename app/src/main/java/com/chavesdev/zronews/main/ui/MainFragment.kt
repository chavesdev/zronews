package com.chavesdev.zronews.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.chavesdev.zronews.R
import com.chavesdev.zronews.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var navController: NavController? = null
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.btnSignin.setOnClickListener { showLogin() }
    }

    private fun showLogin() {
        navController?.navigate(R.id.action_mainFragment_to_loginBottomsheetFragment)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}