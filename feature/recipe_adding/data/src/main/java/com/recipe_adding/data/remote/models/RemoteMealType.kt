package com.recipe_adding.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteMealType(

    @SerialName("id")
    val id: Long = -1,

    @SerialName("title")
    val name: String,
)