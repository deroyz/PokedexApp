package com.example.android.pokedexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.android.pokedexapp.pokemondetail.PokemonDetailScreen
import com.example.android.pokedexapp.pokemonlist.PokemonListScreen
import com.example.android.pokedexapp.ui.theme.PokedexAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexAppTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = DestinationScreen.PokemonList.route
                ) {
                    composable(DestinationScreen.PokemonList.route) {
                        PokemonListScreen(navController = navController)
                    }

                    composable(DestinationScreen.PokemonDetail.route,
                        arguments = listOf(
                            navArgument("dominantColor") {
                                type = NavType.IntType
                            },
                            navArgument("pokemonName") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val dominantColor = remember {
                            val color = it.arguments?.getInt("dominantColor")
                            color?.let { Color(it) } ?: Color.White
                        }
                        val pokemonName = remember {
                            it.arguments?.getString("pokemonName")
                        }
                        PokemonDetailScreen(
                            dominantColor = dominantColor,
                            pokemonName = pokemonName?.toLowerCase(Locale.ROOT) ?: "",
                            navController = navController
                        )
                    }

                }
            }
        }
    }
}

sealed class DestinationScreen(val route: String) {
    object PokemonList : DestinationScreen("pokemon_list_screen")
    object PokemonDetail :
        DestinationScreen("pokemon_detail_screen/{dominantColor}/{pokemonName}") {
        fun createRoute(dominantColor: Color, pokemonName: String) =
            "pokemon_detail_screen/${dominantColor.toArgb()}/$pokemonName"
    }

}
