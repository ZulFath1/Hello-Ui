package com.example.tugas1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tugas1.ui.theme.Tugas1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkTheme by remember {
                mutableStateOf( value = false)
            }

            Tugas1Theme( darkTheme = isDarkTheme) {
                Scaffold( modifier = Modifier.fillMaxSize()) {innerPadding ->
                    UiApp(
                        modifier = Modifier.padding( innerPadding ),
                        isDark = isDarkTheme,
                        onThemeChange = { isDarkTheme = it }
                    )
                }
            }
        }
    }
}

@Composable
fun UiApp(modifier : Modifier = Modifier, isDark: Boolean, onThemeChange: (Boolean) -> Unit) {

    var name by remember { mutableStateOf(value = "") }

    var greetingResult by remember { mutableStateOf(value = "") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Masukkan Nama") },
            modifier = modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (name.isEmpty()) {
                    greetingResult = "Hello Mok!"
                } else {
                    greetingResult = "Hello $name!"
                }
            }
        ) {
            Text("Greeting")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = greetingResult,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = if (isDark) "Dark Mode" else "Light Mode")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = isDark,
                onCheckedChange = { onThemeChange(it) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Tugas1Theme {
        UiApp(isDark = true, onThemeChange = {})
    }
}