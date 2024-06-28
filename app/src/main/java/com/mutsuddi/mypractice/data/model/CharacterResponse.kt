package com.mutsuddi.mypractice.data.model

data class CharacterResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Characters>
)