package com.tjohnn.blog.controller

import com.tjohnn.blog.MySession
import com.tjohnn.blog.domain.models.NewPost
import com.tjohnn.blog.domain.models.Post
import com.tjohnn.blog.services.PostService
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.Parameters
import io.ktor.request.receive
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import org.joda.time.DateTime

fun Route.post(postService: PostService) {

    route("/posts") {

        get("/"){
            val session = call.sessions.get<MySession>()

            if (session == null) {
                call.respondRedirect("/login", permanent = false)
            } else {
                call.respond(FreeMarkerContent("create-post.ftl", null, ""))
            }
        }

        post("/"){
            val parameters = call.receiveParameters()
            val post = NewPost(parameters["title"]!!, parameters["body"]!!, DateTime.now(), DateTime.now())
            postService.createPost(post)
            call.respondRedirect("/posts", permanent = false)
        }

    }

}