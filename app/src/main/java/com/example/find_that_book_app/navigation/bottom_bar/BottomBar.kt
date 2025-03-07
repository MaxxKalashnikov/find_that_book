package com.example.find_that_book_app.navigation.bottom_bar

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.find_that_book_app.R

@Composable
fun BottomBar(navController: NavController){
    val backStackEntry =navController.currentBackStackEntryAsState()
    val tabs = listOf(
        TabItem(stringResource(R.string.popular_books_title), Icons.AutoMirrored.Filled.MenuBook, route = "popular"),
        TabItem(stringResource(R.string.search_title), Icons.Filled.Search, route = "search"),
    )

    NavigationBar(
        modifier = Modifier.height(90.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        tabs.forEach { tab ->
            val selected = tab.route === backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(tab.route){
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    } },
                label = { Text(tab.lable) },
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = null
                    )
                }
            )
        }
    }
}
