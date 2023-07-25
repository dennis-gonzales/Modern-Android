package com.dnnsgnzls.modern.data.api.platforms

import com.dnnsgnzls.modern.data.api.requirements.RequirementsDto
import com.google.gson.annotations.SerializedName

data class PlatformElementDto(
    val platform: PlatformDetailDto,
    @SerializedName("released_at")
    val releasedAt: String? = null,
    @SerializedName("requirements_en")
    val requirementsEn: RequirementsDto? = null,
    @SerializedName("requirements_ru")
    val requirementsRu: RequirementsDto? = null
)