
package com.example.movierating

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.movierating.data.repository.MovieRepository
import com.example.movierating.ui.theme.MovieRatingAppTheme
import androidx.lifecycle.viewmodel.initializer
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movierating.data.db.AppDatabase
import com.example.movierating.data.viewModel.LoginViewModel
import com.example.movierating.data.viewModel.MainViewModel
import com.example.movierating.data.viewModel.WatchedViewModel
import com.example.movierating.ui.screen.LoginScreen
import com.example.movierating.ui.screen.MainScreen
import com.example.movierating.ui.screen.WatchedScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = AppDatabase.get(this)
        val repo = MovieRepository(
            movieDao = db.movieDao(),
            ratingDao = db.ratingDao(),
            userDao = db.userDao(),
            apiKey = "Bearer YOUR_API_KEY"
        )

        val loginVmFactory = viewModelFactory {
            initializer { LoginViewModel(repo) }
        }
        val mainVmFactory = viewModelFactory {
            initializer { MainViewModel(repo) }
        }

        setContent {
            MovieRatingAppTheme {
                val nav = rememberNavController()

                NavHost(navController = nav, startDestination = "login") {
                    composable("login") {
                        val vm = viewModel<LoginViewModel>(factory = loginVmFactory)
                        LoginScreen(vm) { userId ->
                            nav.navigate("main/$userId") { popUpTo("login") { inclusive = true } }
                        }
                    }
                    composable(
                        route = "main/{userId}",
                        arguments = listOf(navArgument("userId") { type = NavType.IntType })
                    ) { backStack ->
                        val userId = backStack.arguments?.getInt("userId")!!
                        val vm = viewModel<MainViewModel>(factory = mainVmFactory)
                        MainScreen(
                            vm = vm,
                            userId = userId,
                            onGoWatched = { nav.navigate("watched/$userId") }
                        )
                    }
                    composable(
                        route = "watched/{userId}",
                        arguments = listOf(navArgument("userId") { type = NavType.IntType })
                    ) { backStack ->
                        val userId = backStack.arguments?.getInt("userId")!!
                        // simple inline factory for WatchedViewModel that needs userId
                        val vm = object : androidx.lifecycle.ViewModelProvider.Factory {
                            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                                @Suppress("UNCHECKED_CAST")
                                return WatchedViewModel(repo, userId) as T
                            }
                        }.let { viewModel (factory = it) }
                        WatchedScreen(vm = vm, onBack = { nav.popBackStack() })
                    }
                }
            }
        }
    }
}
