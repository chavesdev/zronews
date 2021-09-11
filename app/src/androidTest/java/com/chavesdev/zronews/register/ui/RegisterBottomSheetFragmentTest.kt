package com.chavesdev.zronews.register.ui

import com.chavesdev.zronews.MainActivityRobot
import org.junit.Test

class RegisterBottomSheetFragmentTest {

    @Test
    fun checkRegisterModalIsOpeningProperly() {
        MainActivityRobot()
            .startMainActivity()
            .openRegisterDialog()
            .checkRegisterTitle()
            .checkRegisterButtonIsDisabled()
    }

}
