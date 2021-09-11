package com.chavesdev.zronews

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.chavesdev.zronews.main.ui.MainActivity

class MainActivityRobot {

    private lateinit var scenario: ActivityScenario<MainActivity>

    fun startMainActivity() = apply {
        scenario = launchActivity()
    }

    fun openRegisterDialog() = apply {
        scenario.apply {
            onView(withId(R.id.btn_signup)).perform(click())
        }
    }

    fun checkRegisterTitle() = apply {
        scenario.apply {
            onView(ViewMatchers.withText("Criar conta"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    fun checkRegisterButtonIsDisabled() = apply {
        scenario.apply {
            onView(withId(R.id.btn_signup))
                .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
        }
    }

    fun openLoginDialog() = apply {
        scenario.apply {
            onView(withId(R.id.btn_signin)).perform(click())
        }
    }

    fun checkLoginTitle() = apply {
        scenario.apply {
            onView(ViewMatchers.withText("Entrar"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    fun checkLoginButtonIsDisabled() = apply {
        scenario.apply {
            onView(withId(R.id.btn_signin))
                .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
        }
    }

    fun checkLoginButtonIsEnabled() = apply {
        scenario.apply {
            onView(withId(R.id.btn_signin))
                .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
        }
    }

    fun fillLoginForm() = apply {
        scenario.apply {
            fillText(R.id.edt_username, "john@doe.com")
            fillText(R.id.edt_password, "123456")
        }
    }

    fun tapLoginButton() = apply {
        scenario.apply {
            clickOn(R.id.btn_signin)
        }
    }

    fun checkLoginProgressIsVisible() {
        scenario.apply {
            checkIsDisplayed(R.id.content_progress)
        }
    }

    private fun clickOn(id: Int) {
        onView(withId(id)).perform(click())
    }

    private fun fillText(id: Int, text: String) = apply {
        scenario.apply {
            onView(withId(id)).perform(ViewActions.typeText(text))
            closeKeyboard()
        }
    }

    private fun closeKeyboard() {
        Espresso.closeSoftKeyboard()
    }

    private fun checkIsDisplayed(id: Int) {
        onView(withId(id))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}