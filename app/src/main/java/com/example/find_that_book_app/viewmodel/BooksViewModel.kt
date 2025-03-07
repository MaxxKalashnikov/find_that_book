package com.example.find_that_book_app.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.find_that_book_app.model.BookItem
import com.example.find_that_book_app.model.BooksApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

const val API_KEY = "YOUR_API_KEY"

sealed interface BookUiState{
    object Success: BookUiState
    object Error: BookUiState
    object Loading: BookUiState
}

class BooksViewModel : ViewModel() {
    var bookUiState: BookUiState by mutableStateOf(BookUiState.Loading)
        private set
    var bookUiState1: BookUiState by mutableStateOf(BookUiState.Success)
        private set
    var books = mutableStateListOf<BookItem>()
        private set
    var booksSearched by mutableStateOf<List<BookItem>>(emptyList())
        private set
    var searchQuery by mutableStateOf("")
        private set
    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme

    fun getBookList() {
        viewModelScope.launch {
            try {
                val booksApi = BooksApi.getInstance()
                Log.d("BooksViewModel", "Fetching books from API...")
                val response = booksApi.getPopularBooks(query = "''", apiKey = API_KEY)

                books.clear()
                response.items?.let { books.addAll(it) }
                bookUiState = BookUiState.Success
                Log.d("BooksViewModel", "Fetched books: ${books.size}")

            } catch (e: Exception) {
                Log.d("Your ERROR", e.message.toString())
                bookUiState = BookUiState.Error
            }
        }
    }

    fun updateSearchQuery(query: String){
        searchQuery = query
    }

    fun getBooksBySearchQuery(){
        Log.d("BooksViewModel", "Clicked!!!")
        viewModelScope.launch {
            try {
                bookUiState1 = BookUiState.Loading
                val booksApi = BooksApi.getInstance()
                Log.d("BooksViewModel", "Fetching books from API...")
                val response = booksApi.getSearchedBook(query = searchQuery, apiKey = API_KEY)

                booksSearched = response.items ?: emptyList()
                bookUiState1 = BookUiState.Success
                Log.d("BooksViewModel", "Fetched books: ${booksSearched.size}")

            } catch (e: Exception) {
                Log.d("Your ERROR", e.message.toString())
                bookUiState1 = BookUiState.Error
            }
        }
    }

    fun findBookById(bookId: String): BookItem? {
        Log.d("BooksViewModel", "Searching for book ID: $bookId")
        Log.d("BooksViewModel", "Books count: ${books.size}, BooksSearched count: ${booksSearched.size}")

        val foundBook = books.find { it.id == bookId } ?: booksSearched.find { it.id == bookId }

        if (foundBook == null) {
            Log.d("BooksViewModel", "Book not found in either list!")
        } else {
            Log.d("BooksViewModel", "Book found: ${foundBook.volumeInfo.title}")
        }

        return foundBook
    }

    fun toggleTheme() {
        viewModelScope.launch {
            _isDarkTheme.value = !_isDarkTheme.value
        }
    }
}
