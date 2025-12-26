package org.example.projectt.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import movie.composeapp.generated.resources.Res
import movie.composeapp.generated.resources.compose_multiplatform
import org.example.projectt.domain.model.Movie
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun MoviePoster(
 movie: Movie,
 onMoviePosterClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        modifier=modifier
            .clickable{
                onMoviePosterClick()
            }
            .width(140.dp)

    ){
        Card (

            modifier= Modifier
                .width(140.dp)
                .height(210.dp)

        ){
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = null,
                modifier=Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,

            )
        }
        Text(
            text = movie.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium,
        )

    }

}