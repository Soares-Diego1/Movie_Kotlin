package org.example.projectt.domain.model

import kotlin.Int

data class CastMember(
    val id: Int,
    val mainRole: String?,
    val name: String,
    val character: String,
    val profileUrl: String?,
)


//fake objeto
val castMember1 = CastMember (

     id = 1,
 mainRole = "Acting",
 name ="John",
 character = "",
 profileUrl = "url",
)

val castMember2 = CastMember (

    id = 2,
    mainRole = "Actindad",
    name ="ewasdsasf",
    character = "assa",
    profileUrl = "asd",
)


