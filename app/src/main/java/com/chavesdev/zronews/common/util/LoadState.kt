package com.chavesdev.zronews.common.util

sealed class LoadState {
    object READY : LoadState()
    object LOADING : LoadState()
    class SUCCESS(val data : Any) : LoadState()
    class ERROR(val code: String, val msg: String) : LoadState()
}