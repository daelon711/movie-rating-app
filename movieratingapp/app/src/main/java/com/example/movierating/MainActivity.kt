<<<<<<< HEAD:movieratingapp/app/src/main/java/com/example/movierating/MainActivity.kt
package com.example.movierating

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.movierating.ui.screen.MovieListScreen
import com.example.movierating.ui.screen.Movieapp
import com.example.movierating.ui.theme.MovieRatingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieRatingAppTheme {
                Movieapp()
            }
        }
    }
}
=======
package com.example.movierating

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.movierating.ui.theme.MovieRatingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieRatingAppTheme { // in ui theme, has default theme configs
//                Movieapp()
            }
        }
    }
}

>>>>>>> 082d619 (added logic to login and main screens, on how to display content, organized files in folders):app/src/main/java/com/example/movierating/MainActivity.kt
