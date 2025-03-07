package com.example.find_that_book_app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.find_that_book_app.R
import com.example.find_that_book_app.model.BookItem

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RenderBooksList(book: BookItem, navController: NavController){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("bookindividualscreen/${book.id}")
            }
    ) {
        book.volumeInfo.authors?.getOrNull(0)?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )
        }

        GlideImage(
            model = book.volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://")
                ?: stringResource(R.string.url_if_not_available_image),
            contentDescription = stringResource(R.string.book_cover),
            modifier = Modifier.size(250.dp)
        )

        Text(
            text = book.volumeInfo.title,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 4.dp, bottom = 4.dp)
                .fillMaxWidth()
        )

        Text(
            text = book.volumeInfo.publishedDate ?: stringResource(R.string.no_published_date_provided),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
        )

        Text(
            text = book.volumeInfo.subtitle ?: stringResource(R.string.no_subtitle_provided),
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
        )

        HorizontalDivider(color = Color.LightGray, thickness = 12.dp)
    }


}