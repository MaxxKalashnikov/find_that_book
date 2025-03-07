package com.example.find_that_book_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
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
fun BooksSearchScreen(navController: NavController, viewModel: BooksViewModel){
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = { MainTopAppBar(stringResource(R.string.search_books_title), navController) },
        bottomBar = { BottomBar(navController) }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = viewModel.searchQuery,
                    onValueChange = { viewModel.updateSearchQuery(it) },
                    label = { Text(stringResource(R.string.search_books)) },
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                )
                Button(
                    onClick = {
                        keyboardController?.hide()
                        viewModel.getBooksBySearchQuery()
                              },
                    modifier = Modifier
                        .padding(top = 8.dp, end = 8.dp)
                        .height(56.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = stringResource(R.string.search_button),
                        modifier = Modifier.size(24.dp)
                        )
                }
            }

            when(viewModel.bookUiState1){
                is BookUiState.Loading -> LoadingScreen()
                is BookUiState.Success -> {
                    if(viewModel.booksSearched.isNotEmpty()){
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            items(viewModel.booksSearched) { book ->
                                RenderBooksList(book = book, navController)
                            }
                        }
                    }else{
                        Column {
                            Text(
                                text = stringResource(R.string.no_books_found),
                                modifier = Modifier.padding(top = 100.dp)
                            )
                        }
                    }
                }
                is BookUiState.Error -> ErrorScreen()
            }
        }

    }
}