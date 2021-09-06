package com.chavesdev.zronews.register.repo

import com.chavesdev.zronews.common.repo.NetworkMapper
import com.chavesdev.zronews.register.data.remote.models.RegisterResponse
import com.chavesdev.zronews.register.repo.models.RegisterResponseModel

interface RegisterMapper : NetworkMapper<RegisterResponseModel, RegisterResponse>{
}