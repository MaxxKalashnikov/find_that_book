package com.example.find_that_book_app.model

data class GoogleBooksResponse(
    val items: List<BookItem>?
)

data class BookItem(
    val id: String,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo?
)

data class VolumeInfo(
    val title: String,
    val subtitle: String?,
    val authors: List<String>?,
    val imageLinks: ImageLinks?,
    val publishedDate: String?,
    val description: String?,
)

data class ImageLinks(
    val smallThumbnail: String?,
    val thumbnail: String?
)

data class SaleInfo(
    val buyLink: String?
)


