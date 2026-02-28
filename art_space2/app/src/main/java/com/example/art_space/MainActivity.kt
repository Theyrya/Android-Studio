package com.example.art_space

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.art_space.ui.theme.Art_spaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Art_spaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

data class Artwork(
    val imageRes: Int,
    val titleRes: Int,
    val artistRes: Int,
    val year: Int
)

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    val artworks = listOf(
        Artwork(R.drawable.artwork1, R.string.artwork1_title, R.string.artwork1_artist, 2017),
        Artwork(R.drawable.artwork2, R.string.artwork2_title, R.string.artwork2_artist, 2022),
        Artwork(R.drawable.artwork3, R.string.artwork3_title, R.string.artwork3_artist, 2023)
    )

    var currentArtworkIndex by remember { mutableStateOf(0) }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ArtworkDisplay(artwork = artworks[currentArtworkIndex])
            }

            ArtworkNavigation(
                onPrevious = {
                    currentArtworkIndex = if (currentArtworkIndex == 0) {
                        artworks.size - 1
                    } else {
                        currentArtworkIndex - 1
                    }
                },
                onNext = {
                    currentArtworkIndex = (currentArtworkIndex + 1) % artworks.size
                }
            )
        }
    }
}

@Composable
fun ArtworkDisplay(artwork: Artwork) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Image Frame
        Surface(
            modifier = Modifier
                .wrapContentSize()
                .shadow(elevation = 10.dp),
            color = Color.White
        ) {
            Image(
                painter = painterResource(id = artwork.imageRes),
                contentDescription = stringResource(id = artwork.titleRes),
                modifier = Modifier
                    .padding(32.dp)
                    .heightIn(max = 400.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Info Box
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFECEBF4))
                .padding(20.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = artwork.titleRes),
                fontSize = 26.sp,
                fontWeight = FontWeight.Light,
                color = Color.DarkGray
            )
            Row {
                Text(
                    text = stringResource(id = artwork.artistRes),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = " (${artwork.year})",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun ArtworkNavigation(onPrevious: () -> Unit, onNext: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = onPrevious,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Previous")
        }
        Button(
            onClick = onNext,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Art_spaceTheme {
        ArtSpaceApp()
    }
}