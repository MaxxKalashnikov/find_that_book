package com.example.find_that_book_app.navigation.top_bars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.find_that_book_app.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(title: String, navController: NavController){
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ),
        title = { Text(title) },
        actions = {
            IconButton(onClick = {expanded = !expanded}) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = stringResource(R.string.menu_to_navigate_to_info_and_settings)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.info_menu_title)) },
                    onClick = {navController.navigate("info")}
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.settings_menu_item)) },
                    onClick = {navController.navigate("settings")}
                )
            }
        }
    )
}