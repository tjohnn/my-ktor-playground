package com.tjohnn.blog.routes

import com.tjohnn.blog.MySession
import com.tjohnn.blog.controller.HomeController
import io.ktor.application.call
import io.ktor.auth.UserHashedTableAuth
import io.ktor.auth.UserIdPrincipal
import io.ktor.auth.authenticate
import io.ktor.auth.principal
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.ContentType
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.sessions.clear
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.ktor.sessions.set


fun Route.blogRoutes(){

    // static files
    static("/static") {
        resources("static")
    }

    get("/") {
        call.respondText("<h1 style='color:green;'>My first ktor app</h1><div><a  href='/login'>Login</a></div>", ContentType.Text.Html)
    }


    route("/login") {
        get {
            val session = call.sessions.get<MySession>()

            if (session != null) {
                call.respondRedirect("/dashboard", permanent = false)
                return@get
            }

            call.respond(FreeMarkerContent("login.ftl", null, ""))
        }
        authenticate("login") {
            post {

                val session = call.sessions.get<MySession>()

                if (session != null) {
                    call.respondRedirect("/dashboard", permanent = false)
                    return@post
                }

                val principal = call.principal<UserIdPrincipal>()

                if(principal == null){
                    call.respond(FreeMarkerContent("login.ftl", mapOf("error" to "Invalid login"), ""))
                } else {
                    call.sessions.set( MySession(principal.name))

                    call.respondRedirect("/dashboard", permanent = false)
                }
            }
        }
    }
    get("/dashboard") {
        val session = call.sessions.get<MySession>()

        if (session == null) {
            call.respondRedirect("/login", permanent = false)
        } else {
            println("Session name is: ${session.username}")
            call.respond(FreeMarkerContent("index.ftl", mapOf("data" to HomeController.getPersons()), ""))
        }


    }

    get("/logout") {
        call.sessions.clear<MySession>()
        call.respondRedirect("/", permanent = false)
    }
}