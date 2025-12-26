package org.example.projectt.data.mapper

import org.example.projectt.data.network.model.GenreResponse
import org.example.projectt.domain.model.Genre

fun GenreResponse.toModel() = Genre(
    id =this.id,
    name= this.name,
)