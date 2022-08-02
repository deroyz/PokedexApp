package com.example.android.pokedexapp.repository

import com.example.android.pokedexapp.data.remote.PokeApi
import com.example.android.pokedexapp.util.Resource
import com.example.android.pokedexapp.data.remote.responses.Pokemon
import com.example.android.pokedexapp.data.remote.responses.PokemonList
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
) {
    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {

        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured: ${e.localizedMessage}}")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {

        val response = try {
            api.getPokemon(pokemonName)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured: ${e.localizedMessage}}")
        }
        return Resource.Success(response)
    }
}

//commit