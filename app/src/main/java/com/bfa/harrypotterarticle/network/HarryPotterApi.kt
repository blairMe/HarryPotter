package com.bfa.harrypotterarticle.network

import com.bfa.harrypotterarticle.api.Characters
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface HarryPotterApi {
    @GET(value = "api/characters")
    suspend fun getCharacters() : Characters
}