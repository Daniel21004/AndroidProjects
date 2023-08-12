package com.example.greatingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greatingcard.ui.theme.GreatingCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreatingCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

/*
*  @Composable -> For functions, For JetPack Compose
*  Characteristic:
*       - The function has the Compasable label
*       - The name function is capitalized
*       - The function cant return anything value
* */
@Composable // Interface component
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(color = Color.Cyan) { // The wrapper 'Surface' has a parameter 'color'
        Text(
            text = "Hello $name!",
            modifier = Modifier.padding(24.dp) // Modifier some styles of component
        )
    }
}

@Preview(showBackground = true) // True, you can watch the background white (default). False, you cant watch anything background.
@Composable
fun GreetingPreview() {
    GreatingCardTheme {
        Greeting("Jorge")
    }
}

@Preview(showBackground = false) // Label for preview the component
@Composable
fun Prueba() {
    GreatingCardTheme {
        Greeting("Jorsh")
    }
}