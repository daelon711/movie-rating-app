
package com.example.movierating.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.TextField

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var username by remember { mutableStateOf("") } // remembers value so its not resetted
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier // modifier is called
            .fillMaxSize() // fills content to max size it can
            .padding(16.dp),
        verticalArrangement = Arrangement.Center // arranges in center vertically
    ) {
        TextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
        Spacer(Modifier.height(8.dp))
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
        Spacer(Modifier.height(16.dp))
        Button(onClick = onLoginSuccess, modifier = Modifier.align(Alignment.End)) {
            Text("Login")
        }
    }
}
