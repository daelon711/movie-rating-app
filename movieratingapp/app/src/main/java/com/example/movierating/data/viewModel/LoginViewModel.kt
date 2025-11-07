// observing state like logged in or not and functions for changing state like logging in , these ask repository\\
//
// ui/login/LoginViewModel.kt
package com.example.movierating.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movierating.data.db.entityRoom.UserEntity
import com.example.movierating.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LoginState(
    val username: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val error: String? = null,
    val user: UserEntity? = null
)

class LoginViewModel(private val repo: MovieRepository): ViewModel() { // returns viewmodel(
    private val _state = MutableStateFlow(LoginState()) //saves as mutable, where does loginstate come from
    val state: StateFlow<LoginState> = _state // why is this made

    fun updateUsername(v: String) { _state.value = _state.value.copy(username = v) }
    fun updatePassword(v: String) { _state.value = _state.value.copy(password = v) }

    fun submit() {
        val s = _state.value
        viewModelScope.launch {
            _state.value = s.copy(loading = true, error = null)
            runCatching { repo.login(s.username, s.password) }
                .onSuccess { user -> _state.value = _state.value.copy(loading = false, user = user) }
                .onFailure { e -> _state.value = _state.value.copy(loading = false, error = e.message) }
        }
    }
}
