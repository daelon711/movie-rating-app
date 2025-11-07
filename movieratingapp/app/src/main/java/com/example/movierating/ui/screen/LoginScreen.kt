// ui/login/LoginScreen.kt
package com.example.movierating.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movierating.data.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    vm: LoginViewModel,
    onLoggedIn: (userId: Int) -> Unit
) {
    val state by vm.state.collectAsState() // is collectasstate function built in that perceives state? what typa state values are there
    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = state.username, onValueChange = vm::updateUsername,
            label = { Text("Username") }, modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = state.password, onValueChange = vm::updatePassword,
            label = { Text("Password") }, modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Button(onClick = vm::submit, enabled = !state.loading) {
            Text(if (state.loading) "Logging in..." else "Login")
        }
        state.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        state.user?.let { onLoggedIn(it.id) }
    }
}
