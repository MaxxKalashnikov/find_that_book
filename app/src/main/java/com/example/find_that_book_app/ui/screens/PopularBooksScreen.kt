package com.example.find_that_book_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.find_that_book_app.R
import com.example.find_that_book_app.navigation.bottom_bar.BottomBar
import com.example.find_that_book_app.navigation.top_bars.MainTopAppBar
import com.example.find_that_book_app.ui.components.ErrorScreen
import com.example.find_that_book_app.ui.components.LoadingScreen
import com.example.find_that_book_app.ui.components.RenderBooksList
import com.example.find_that_book_app.viewmodel.BookUiState
import com.example.find_that_book_app.viewmodel.BooksViewModel

@Composable
fun PopularBooksScreen(navController: NavController, viewModel: BooksViewModel){

    LaunchedEffect(Unit) {
        viewModel.getBookList()
    }

    Scaffold(
        topBar = { MainTopAppBar(stringResource(R.string.top_20_in_finland), navController) },
        bottomBar = { BottomBar(navController) }
    ) {innerPadding ->
        when(viewModel.bookUiState){
            is BookUiState.Loading -> LoadingScreen()
            is BookUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(viewModel.books){ book ->
                        RenderBooksList(book = book, navController)
                    }
                }
            }
            is BookUiState.Error -> ErrorScreen()
        }
    }
}