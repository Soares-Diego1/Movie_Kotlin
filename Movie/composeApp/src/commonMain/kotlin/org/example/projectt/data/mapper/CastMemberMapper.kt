package org.example.projectt.data.mapper

import org.example.projectt.data.network.IMAGE_BASE_URL
import org.example.projectt.data.network.model.CastMemberResponse
import org.example.projectt.domain.model.CastMember
import org.example.projectt.domain.model.ImageSize

fun CastMemberResponse.toModel() = CastMember(
    id = this.id,
    name = this.name,
    mainRole = this.department,
    character = this.character,
    profileUrl = "$IMAGE_BASE_URL/${ImageSize.X_MALL.size}/${this.profilePath}",
)