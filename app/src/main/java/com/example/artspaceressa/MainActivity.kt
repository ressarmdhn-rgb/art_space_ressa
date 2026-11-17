package com.example.artspaceressa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceressa.ui.theme.ArtSpaceRessaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceRessaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

data class Artwork(
    @param:DrawableRes val imageRes: Int,
    @param:StringRes val titleRes: Int,
    @param:StringRes val artistRes: Int,
    @param:StringRes val yearRes: Int,
    @param:StringRes val descriptionRes: Int
)

val artworkList = listOf(
    Artwork(
        imageRes = R.drawable.kapal_dilanda_badai,
        titleRes = R.string.artwork_1_title,
        artistRes = R.string.artwork_1_artist,
        yearRes = R.string.artwork_1_year,
        descriptionRes = R.string.artwork_1_description
    ),
    Artwork(
        imageRes = R.drawable.raden_saleh___diponegoro_arrest,
        titleRes = R.string.artwork_2_title,
        artistRes = R.string.artwork_2_artist,
        yearRes = R.string.artwork_2_year,
        descriptionRes = R.string.artwork_2_description
    ),
    Artwork(
        imageRes = R.drawable.kawan_republik,
        titleRes = R.string.artwork_3_title,
        artistRes = R.string.artwork_3_artist,
        yearRes = R.string.artwork_3_year,
        descriptionRes = R.string.artwork_3_description
    ),
    Artwork(
        imageRes = R.drawable.nighthawks,
        titleRes = R.string.artwork_4_title,
        artistRes = R.string.artwork_4_artist,
        yearRes = R.string.artwork_4_year,
        descriptionRes = R.string.artwork_4_description
    ),
    Artwork(
        imageRes = R.drawable.the_false_mirror_1928,
        titleRes = R.string.artwork_5_title,
        artistRes = R.string.artwork_5_artist,
        yearRes = R.string.artwork_5_year,
        descriptionRes = R.string.artwork_5_description
    )
)

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    var currentIndex by rememberSaveable { mutableIntStateOf(0) }
    val currentArtwork = artworkList[currentIndex]

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Artwork Wall / Display Image
        ArtworkWall(
            imageRes = currentArtwork.imageRes,
            contentDescriptionRes = currentArtwork.titleRes
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Artwork Descriptor (Title, Description, Artist, Year)
        ArtworkDescriptor(
            titleRes = currentArtwork.titleRes,
            artistRes = currentArtwork.artistRes,
            yearRes = currentArtwork.yearRes,
            descriptionRes = currentArtwork.descriptionRes
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Controller Buttons (Previous / Next)
        DisplayController(
            onPreviousClick = {
                currentIndex = if (currentIndex == 0) artworkList.size - 1 else currentIndex - 1
            },
            onNextClick = {
                currentIndex = (currentIndex + 1) % artworkList.size
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun ArtworkWall(
    @DrawableRes imageRes: Int,
    @StringRes contentDescriptionRes: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(4.dp)),
        color = Color.White,
        shape = RoundedCornerShape(4.dp)
    ) {
        Box(
            modifier = Modifier.padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = stringResource(id = contentDescriptionRes),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun ArtworkDescriptor(
    @StringRes titleRes: Int,
    @StringRes artistRes: Int,
    @StringRes yearRes: Int,
    @StringRes descriptionRes: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(id = titleRes),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = descriptionRes),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(id = artistRes))
                    }
                    append(" (")
                    append(stringResource(id = yearRes))
                    append(")")
                },
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun DisplayController(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = Modifier.width(140.dp)
        ) {
            Text(text = stringResource(id = R.string.previous))
        }

        Button(
            onClick = onNextClick,
            modifier = Modifier.width(140.dp)
        ) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceRessaTheme {
        ArtSpaceApp()
    }
}
