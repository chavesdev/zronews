package com.chavesdev.zronews.login.ui

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.chavesdev.zronews.R
import com.chavesdev.zronews.main.ui.MainActivity
import org.junit.Test

class LoginBottomsheetFragmentTest {

    @Test
    fun checkLoginFormIsOpening() {
        launchActivity<MainActivity>().apply {
            openDialog()
            onView(withText("Entrar")).check(matches(isDisplayed()))
            onView(withId(R.id.btn_signin)).check(matches(isNotEnabled()))
        }
    }

    @Test
    fun checkFillingFormIsEnablingButton() {
        launchActivity<MainActivity>().apply {
            openDialog()
            fillText(R.id.edt_username, "john@doe.com")
            fillText(R.id.edt_password, "123456")
            onView(withId(R.id.btn_signin)).check(matches(isEnabled()))
        }
    }

    @Test
    fun checkProgressIsShowingAfterFillForm() {
        launchActivity<MainActivity>().apply {
            openDialog()
            fillText(R.id.edt_username, "john@doe.com")
            fillText(R.id.edt_password, "123456")
            clickOn(R.id.btn_signin)
            checkIsDisplayed(R.id.content_progress)
        }
    }

    //UIAutomator
    private fun openDialog() {
        onView(withId(R.id.btn_signin)).perform(click())
    }

    private fun closeKeyboard() {
        Espresso.closeSoftKeyboard()
    }

    private fun fillText(id: Int, text: String) {
        onView(withId(id)).perform(typeText(text))
        closeKeyboard()
    }

    private fun clickOn(id: Int) {
        onView(withId(id)).perform(click())
    }

    private fun checkIsDisplayed(id: Int) {
        onView(withId(id)).check(matches(isDisplayed()))
    }
}