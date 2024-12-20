package model

import kotlinx.serialization.Serializable

@Serializable
data class BirdImage(
    val author: String,
    val category: String,
    val path: String,
)

@Serializable
data class BirdImageList(
    val birdImageList: List<BirdImage>
)
