package com.tjohnn.blog.controller

import com.tjohnn.blog.domain.models.Person

class HomeController {
    companion object {
        fun getPersons(): List<Person> {
            return  listOf(
                    Person("John", "Doe", "john@gmail.com"),
                    Person("Tosin", "Adeoye", "tosin@gmail.com"),
                    Person("Gabriel", "Lyla", "geb@gmail.com"),
                    Person("Jonah", "Mira", "jonah@gmail.com"),
                    Person("David", "Wesley", "david@gmail.com")
            )
        }
    }
}