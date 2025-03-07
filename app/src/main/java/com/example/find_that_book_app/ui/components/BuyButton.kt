package com.example.find_that_book_app.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.find_that_book_app.R

@Composable
fun BuyLinkButton(buyLink: String?) {
    val context = LocalContext.current

    buyLink?.takeIf { it.isNotEmpty() }?.let { link ->
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(stringResource(R.string.read_now_button_text))
        }
    } ?: Text(
        text = stringResource(R.string.buy_link_not_available),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        textAlign = TextAlign.Center,
        color = Color.Gray
    )
}
