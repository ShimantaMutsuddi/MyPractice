package com.mutsuddi.mypractice.data.remote

import com.mutsuddi.mypractice.data.model.CharacterResponse
import com.mutsuddi.mypractice.data.model.StarshipList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("people/?page")
    suspend fun getCharacters(@Query ("page") page: Int): Response<CharacterResponse>
}