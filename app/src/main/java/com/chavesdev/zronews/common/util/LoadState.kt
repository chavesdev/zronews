package com.chavesdev.zronews.common.util

import com.chavesdev.zronews.common.repo.models.BaseResponseModel

sealed class LoadState {
    object READY : LoadState()
    object LOADING : LoadState()
    class SUCCESS(val data : Any?) : LoadState()
    class ERROR(val code: String? = null, val msg: String? = null, val errors:List<Any>? = null) : LoadState()
}