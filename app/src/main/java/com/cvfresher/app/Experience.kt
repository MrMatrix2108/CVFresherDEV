package com.cvfresher.app

import kotlinx.serialization.Serializable

@Serializable
data class Experience(
    val employer: String,
    val jobTitle: String,
    val yearStart: Int,
    val yearEnd: Int?,
    val description: String,
    val isCurrent: Boolean
) {
}