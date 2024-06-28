package com.mutsuddi.mypractice.data.model

data class StarshipList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Starship>
)