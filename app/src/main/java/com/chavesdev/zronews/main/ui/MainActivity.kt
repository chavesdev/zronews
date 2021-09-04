package com.chavesdev.zronews.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chavesdev.zronews.R
import com.chavesdev.zronews.databinding.ActivityMainBinding
import com.chavesdev.zronews.login.ui.LoginBottomsheetFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnSignin.setOnClickListener {
            showLogin()
        }
    }

    private fun showLogin() {
        LoginBottomsheetFragment.newInstance()
            .show(supportFragmentManager, LoginBottomsheetFragment.tag)
    }
}