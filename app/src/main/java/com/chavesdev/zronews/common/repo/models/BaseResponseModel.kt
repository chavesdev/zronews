package com.chavesdev.zronews.common.repo.models

abstract class BaseResponseModel {
    var code: String? =
        null // A ideia aqui era refatorar esse code para o enum, pois eu so percebi depois que alguns valores se repetiam no back
    var message: String? = null
}