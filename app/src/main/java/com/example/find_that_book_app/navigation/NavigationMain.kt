package com.example.find_that_book_app.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.find_that_book_app.ui.screens.BookIndividualScreen
import com.example.find_that_book_app.ui.screens.BooksSearchScreen
import com.example.find_that_book_app.ui.screens.InfoScreen
import com.example.find_that_book_app.ui.screens.PopularBooksScreen
import com.example.find_that_book_app.ui.screens.SettingsScreen
import com.example.find_that_book_app.viewmodel.BooksViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationMain(viewModel: BooksViewModel){
    val navController = rememberNavController()
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        ){
        NavHost(
            navController = navController,
            startDestination = "popular"
        ){
            composable(route = "popular"){
                PopularBooksScreen(navController, viewModel)
            }
            composable(route = "search"){
                BooksSearchScreen(navController, viewModel)
            }
            composable(route = "info"){
                InfoScreen(navController)
            }
            composable(route = "settings"){
                SettingsScreen(navController, viewModel)
            }
            composable(route = "bookindividualscreen/{bookId}"){ backStackEntry ->
                val bookId = backStackEntry.arguments?.getString("bookId")
                BookIndividualScreen(navController, bookId = bookId, viewModel)
            }
        }
    }
}