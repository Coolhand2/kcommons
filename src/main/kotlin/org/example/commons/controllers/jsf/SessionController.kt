package org.example.commons.controllers.jsf

import org.example.commons.entities.User
import java.io.Serializable
import javax.annotation.PostConstruct
import javax.ejb.Stateful

@Stateful(name = "session")
class SessionController : Serializable {
    lateinit var user: User


    @PostConstruct
    fun initialize() {
    }
}