package ru.wildberries.flights.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.wildberries.flights.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean,
    val likes: Int = 0,
) {
    fun toDto() = Post(id, author, content, published, likedByMe, likes)

    companion object {
        fun fromDto(dto: Post) =
            PostEntity(dto.id, dto.author, dto.content, dto.published, dto.likedByMe, dto.likes)

    }
}
