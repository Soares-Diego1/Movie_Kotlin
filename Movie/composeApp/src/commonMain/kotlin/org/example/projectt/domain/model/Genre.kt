package org.example.projectt.domain.model

import kotlin.Int

data class Genre(


    val id: Int,
    val name: String,
)

val genre1 = Genre(
     id=1,
 name="Acting"
)

val genre2 = Genre(
    id=2,
    name="adventure"
)