package com.chavesdev.zronews.auth

import android.content.Context
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class AuthManagerImplTest {

    private lateinit var authManager: AuthManager

    private val context: Context = mockk(relaxed = true)
    private val token = "x"

    @Before
    fun setUp() {
        authManager = AuthManagerImpl(context)
    }

    @Test
    fun checkStoreIsworking() {
        //given
        every { authManager.getToken() } returns token

        //when
        authManager.storeToken(token)

        //then
        assert(authManager.getToken() == token)
        assert(authManager.getBearerToken() == "Bearer $token")
    }
}