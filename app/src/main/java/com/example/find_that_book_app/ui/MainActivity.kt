package com.example.find_that_book_app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.find_that_book_app.navigation.NavigationMain
import com.example.find_that_book_app.ui.theme.Find_that_book_Theme
import com.example.find_that_book_app.viewmodel.BooksViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: BooksViewModel = viewModel()
            Find_that_book_Theme(viewModel) {
                NavigationMain(viewModel)
            }
        }
    }
}
