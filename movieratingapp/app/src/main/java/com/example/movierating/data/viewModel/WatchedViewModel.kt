// ui/watched/WatchedViewModel.kt
package com.example.movierating.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movierating.data.db.dao.RatedMovieRow
import com.example.movierating.data.repository.MovieRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class WatchedViewModel( val repo: MovieRepository, userId: Int): ViewModel() {
    val rated: StateFlow<List<RatedMovieRow>> =
        repo.getUserRatedMovies(userId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
