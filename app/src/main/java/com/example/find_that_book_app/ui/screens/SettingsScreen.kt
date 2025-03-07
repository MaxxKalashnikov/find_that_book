package com.example.find_that_book_app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.find_that_book_app.R
import com.example.find_that_book_app.navigation.top_bars.TopBar
import com.example.find_that_book_app.viewmodel.BooksViewModel

@Composable
fun SettingsScreen(navController: NavController, viewModel: BooksViewModel){
    Scaffold(
        topBar = { TopBar(stringResource(R.string.settings), navController) }
    ) {innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.dark_mode),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                Switch(
                    modifier = Modifier.padding(end = 8.dp),
                    checked = viewModel.isDarkTheme.collectAsState().value,
                    onCheckedChange = { viewModel.toggleTheme() }
                )
            }
        }
    }
}