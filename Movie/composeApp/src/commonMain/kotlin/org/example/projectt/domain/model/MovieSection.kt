package org.example.projectt.domain.model

data class MovieSection(
    val sectionType: SectionType,
    val movies: List<Movie>,
){
    enum class SectionType{
        POPULAR,
        TOP_RATED,
        UPCOMING,
    }
}
