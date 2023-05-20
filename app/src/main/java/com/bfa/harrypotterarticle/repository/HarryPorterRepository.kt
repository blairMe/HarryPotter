package com.bfa.harrypotterarticle.repository

import com.bfa.harrypotterarticle.api.Characters
import com.bfa.harrypotterarticle.network.HarryPotterApi
import com.bfa.harrypotterarticle.utils.DataOrException
import javax.inject.Inject

class HarryPotterRepository @Inject constructor(private val api : HarryPotterApi) {
    suspend fun getCharacters() :
            DataOrException<Characters, Boolean, Exception> {
        val response = try {
            api.getCharacters()
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}