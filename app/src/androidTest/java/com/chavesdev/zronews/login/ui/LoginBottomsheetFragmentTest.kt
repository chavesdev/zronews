package com.chavesdev.zronews.login.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.chavesdev.zronews.MainActivityRobot
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginBottomsheetFragmentTest {

    @Test
    fun checkLoginFormIsOpening() {
        MainActivityRobot()
            .startMainActivity()
            .openLoginDialog()
            .checkLoginTitle()
            .checkLoginButtonIsDisabled()
    }

    @Test
    fun checkFillingFormIsEnablingButton() {
        MainActivityRobot()
            .startMainActivity()
            .openLoginDialog()
            .fillLoginForm()
            .checkLoginButtonIsEnabled()
    }

    @Test
    fun checkProgressIsShowingAfterFillForm() {
        MainActivityRobot()
            .startMainActivity()
            .openLoginDialog()
            .fillLoginForm()
            .tapLoginButton()
            .checkLoginProgressIsVisible()
    }
}