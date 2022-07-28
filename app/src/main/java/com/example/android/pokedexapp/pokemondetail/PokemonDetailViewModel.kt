package com.example.android.pokedexapp.pokemondetail

import androidx.lifecycle.ViewModel
import com.example.android.pokedexapp.repository.PokemonRepository
import com.example.android.pokedexapp.util.Resource
import com.example.android.pokedexapp.data.remote.responses.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {

    suspend fun getPokemonInfo (pokemonName: String): Resource<Pokemon>{
        return repository.getPokemonInfo(pokemonName)
    }
}