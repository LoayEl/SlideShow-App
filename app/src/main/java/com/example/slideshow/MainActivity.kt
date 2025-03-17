package com.example.slideshow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.slideshow.ui.theme.SlideShowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlideShowTheme {
                    SlideShow()
                }
            }
        }
    }


@Composable
fun Greeting( modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(value = 1) }
    var inputText by remember { mutableStateOf("")}

        val imageResource=when(result){
        1-> R.drawable.egyptflag
        2-> R.drawable.pyramids
        3-> R.drawable.spinx
        4-> R.drawable.abu_simbel
        else -> R.drawable.pyramids
    }
    val imageDescription = when (result) {
        1 -> "Landmarks of Egypt"
        2 -> "The Great Pyramids of Giza"
        3 -> "The Great Sphinx of Giza"
        4 -> "The Abu Simbel temples"
        else -> "The Great Pyramids of Giza"
    }

    Box( modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFC2B280)) // Apply full background color
        .padding(16.dp),
        contentAlignment = Alignment.Center) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = "1",
                modifier = Modifier
                    .border(4.dp, Color.Black)  // 4dp thick black border
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = imageDescription,
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(19.dp))


            TextField( value = inputText,
                onValueChange = { newValue ->
                    inputText = newValue.filter { it.isDigit() } // Only allow numbers
                },
                label = { Text("Slide Number (1-4)") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Button(
                onClick = {
                    val selectedSlide = inputText.toIntOrNull()
                    if (selectedSlide in 1..4) {
                        if (selectedSlide != null) {
                            result = selectedSlide
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray,
                    contentColor = Color.White
                )
            ) {
                Text("Go to Slide")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {  result = if (result > 1) result - 1 else 3},
                    colors=ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Next Slide")

                }
                Spacer(modifier = Modifier.width(150.dp))

                Button(onClick = {  result = if (result < 4) result + 1 else 1  },
                    colors=ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                    ) {
                    Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "Next Slide")

                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun SlideShow() {
    SlideShowTheme {
        Greeting(modifier = Modifier.fillMaxSize().padding(16.dp))
    }
}