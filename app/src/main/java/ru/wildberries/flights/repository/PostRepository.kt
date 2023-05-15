package ru.wildberries.flights.repository

import androidx.lifecycle.LiveData
import ru.wildberries.flights.dto.Post

interface PostRepository {
    fun getAll(): List<Post>
    fun likeById(id: Long)
    fun save(post: Post)
    fun removeById(id: Long)
}
