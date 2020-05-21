package org.example.commons.services

import org.example.commons.repositories.api.UserRepository
import org.example.commons.services.api.UserService
import javax.inject.Inject

object UserServiceImpl : UserService {
    @Inject
    private val users: UserRepository? = null
}