package com.tjohnn.blog.domain.models

import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime
import java.time.LocalDateTime

object Posts : Table() {
    val id = long("id").primaryKey().autoIncrement()
    val title = varchar("title", 255)
    val body = varchar("body", 4_000)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}


data class Post (
        val id: Int,
        val title: String,
        val body: String,
        val createdAt: DateTime,
        val updatedAt: DateTime
)

data class NewPost (
        val title: String,
        val body: String,
        val createdAt: DateTime,
        val updatedAt: DateTime
)
