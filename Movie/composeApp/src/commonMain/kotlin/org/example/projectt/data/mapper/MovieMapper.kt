package org.example.projectt.data.mapper

import org.example.projectt.data.network.IMAGE_BASE_URL
import org.example.projectt.data.network.model.CastMemberResponse
import org.example.projectt.data.network.model.MoviesResponse
import org.example.projectt.domain.model.ImageSize
import org.example.projectt.domain.model.Movie
import org.example.projectt.utils.formatRating
import kotlin.math.roundToInt


fun MoviesResponse.toModel(
    castMembersResponse: List<CastMemberResponse>?=null,
    movieTrailerYoutubeKey: String? = null,
imageSize: ImageSize = ImageSize.SMALL,
) =  Movie(
    id = this.id,
    title = this.title,
    overview = this.overview,
    posterUrl = "$IMAGE_BASE_URL/${imageSize.size}/${this.posterPath}",
    genres = this.genre?.map { it.toModel() },
    year =this.getYearFromReleaseDate() ,
    duration =this.getDurationInHoursAndMinutes() ,
    rating =this.voteArerage.formatRating(),
    castMembers = castMembersResponse
        ?.filter {it.department == "Acting" }

        ?.take(20)
        ?.map{it.toModel()},

movieTrailerYoutubeKey = movieTrailerYoutubeKey,
)
private  fun MoviesResponse.getYearFromReleaseDate(): Int{
    return this.releaseDate.year
}
private fun MoviesResponse.getDurationInHoursAndMinutes(): String?{
    return this.runtime?.let { runtimeMinutes->
        val hours = runtimeMinutes /60
        val minutes = runtimeMinutes % 60

         buildString {
            if(hours>0){
                append("${hours}h ")
            }
            append("${minutes}min")
        }
    }
}