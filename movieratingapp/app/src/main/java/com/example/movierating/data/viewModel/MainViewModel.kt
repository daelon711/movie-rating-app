package com.example.movierating.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movierating.data.MovieEntity
import com.example.movierating.data.repository.MovieRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val repo: MovieRepository): ViewModel() {
    val movies: StateFlow<List<MovieEntity>> =
        repo.getTopMoviesFlow()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun initFetchOnce() {
        viewModelScope.launch { repo.ensureMoviesFetchedOnce() }
    }// fetching movie api once

    fun rate(userId: Int, movieId: Int, rating: Int) {
        viewModelScope.launch { repo.rateMovie(userId, movieId, rating) }
    }
}
