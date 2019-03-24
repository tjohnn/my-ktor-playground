package com.tjohnn.blog

import com.tjohnn.blog.controller.post
import com.tjohnn.blog.routes.blogRoutes
import com.tjohnn.blog.services.PostService
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.freemarker.FreeMarker
import io.ktor.http.ContentType
import io.ktor.locations.Locations
import io.ktor.response.respondText
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import service.DatabaseFactory

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)


// embedded server
/*fun main(args: Array<String>) {
    val server = embeddedServer(Netty, 8080, module = Application::mainModule)
    server.start(wait = true)
}*/

fun Application.mainModule(){
    install(CallLogging)
    install(DefaultHeaders)

    // enable template loading
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

    // Allows to use classes annotated with @Location to represent URLs.
    // They are typed, can be constructed to generate URLs, and can be used to register routes.
    install(Locations)

    install(Sessions) {
        cookie<MySession>("SESSION")
    }

    install(Authentication) {
        form("login") {

            skipWhen { call -> call.sessions.get<MySession>() != null }

            userParamName = "username"
            passwordParamName = "password"
            challenge = FormAuthChallenge.Unauthorized
            validate { credentials ->
                println("Credential is : ${credentials.name}  ${credentials.password}")
                if (!credentials.name.isEmpty() && credentials.name == credentials.password)
                    UserIdPrincipal(credentials.name)
                else null }
        }
    }

    DatabaseFactory.init()

    val postService = PostService()


    routing {
        blogRoutes()
        post(postService)
    }
}

data class MySession(val username: String)