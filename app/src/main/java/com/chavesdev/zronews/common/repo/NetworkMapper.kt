package com.chavesdev.zronews.common.repo

interface NetworkMapper<Model, NetworkModel> {

    fun toModel(networkModel: NetworkModel) : Model

    fun toNetwork(model: Model) : NetworkModel
}