package com.example.intents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.intents.ui.theme.IntentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            getInfo()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun getInfo() {
    var input by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {
            TextField(
                value = input,
                onValueChange = { input = it },
                label = { Text("Input Local") }
            )
            Box(
//                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { input = "" }
                    ) {
                        Text(text = "Limpar")
                    }

                    Button(
                        onClick = {
                            val query = Uri.encode(input)
                            val url = "https://www.google.com/maps/search/?api=1&query=$query"
                            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

                            if (browserIntent.resolveActivity(context.packageManager) != null) {
                                context.startActivity(browserIntent)
                            } else {
                                Toast.makeText(
                                    context,
                                    "No browser available to open the map",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    ) {
                        Text(text = "Enviar")
                    }
                }
            }
        }
    }

}
