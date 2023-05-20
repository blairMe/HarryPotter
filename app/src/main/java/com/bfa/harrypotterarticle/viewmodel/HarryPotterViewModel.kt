package com.bfa.harrypotterarticle.viewmodel

import androidx.lifecycle.ViewModel
import com.bfa.harrypotterarticle.api.Characters
import com.bfa.harrypotterarticle.repository.HarryPotterRepository
import com.bfa.harrypotterarticle.utils.DataOrException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HarryPotterViewModel @Inject constructor(private val repository : HarryPotterRepository)
    : ViewModel()  {
    suspend fun getCharactersData() :
            DataOrException<Characters, Boolean, Exception> {
        return repository.getCharacters()
    }
}