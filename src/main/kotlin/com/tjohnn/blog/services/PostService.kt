package com.tjohnn.blog.services

import com.tjohnn.blog.domain.models.NewPost
import com.tjohnn.blog.domain.models.Posts
import org.jetbrains.exposed.sql.insert
import org.joda.time.DateTime
import service.DatabaseFactory.dbQuery

class PostService {

    suspend fun createPost(post: NewPost): Long {
        var id: Long = 0
        dbQuery {
            id = Posts.insert {
                it[title] = post.title
                it[body] = post.body
                it[createdAt] = DateTime.now()
                it[updatedAt] = DateTime.now()
            }[Posts.id]!!
        }
        println("New post id is $id")
        return id;
    }

}