package com.example.find_that_book_app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.find_that_book_app.R
import com.example.find_that_book_app.navigation.bottom_bar.BottomBar
import com.example.find_that_book_app.navigation.top_bars.TopBar
import com.example.find_that_book_app.ui.components.BuyLinkButton
import com.example.find_that_book_app.viewmodel.BooksViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookIndividualScreen(navController: NavController, bookId: String?, viewModel: BooksViewModel){
    val books = viewModel.findBookById("$bookId")
    Scaffold(
        topBar = {
            if (books != null) {
                TopBar(books.volumeInfo.title, navController)
            }else{
                TopBar("", navController)
            }
        },
        bottomBar = { BottomBar(navController) }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.Top
            ) {
                GlideImage(
                    model = books?.volumeInfo?.imageLinks?.thumbnail?.replace("http://", "https://")
                        ?: "https://upload.wikimedia.org/wikipedia/commons/6/64/Poster_not_available.jpg",
                    contentDescription = stringResource(R.string.book_cover),
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f) // Takes remaining space
                ) {
                    Text(
                        text = books?.volumeInfo?.title ?: stringResource(R.string.no_title),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    books?.volumeInfo?.subtitle?.let { subtitle ->
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.bodyLarge,
                            fontStyle = FontStyle.Italic
                        )
                    }
                    Text(
                        text = "Author(s): ${books?.volumeInfo?.authors?.joinToString(", ") ?: stringResource(R.string.unknown)}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Published Date: ${books?.volumeInfo?.publishedDate ?: stringResource(R.string.unknown)}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Text(
                text = books?.volumeInfo?.description ?: stringResource(R.string.no_description_available),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            BuyLinkButton(books?.saleInfo?.buyLink)
        }

    }
}