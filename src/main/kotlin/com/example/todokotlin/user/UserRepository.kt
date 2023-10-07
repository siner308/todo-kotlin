package com.example.todokotlin.user

import org.springframework.stereotype.Repository

@Repository
class UserRepository {
    private val users = mutableListOf<User>()

    fun getById(id: Int): User? {
        return this.users.firstOrNull { it.id == id }
    }

    fun save(user: User) {
        val index = this.users.indexOfFirst { it.id == user.id }
        if (index == -1) {
            user.id = this.getMaxId() + 1
            this.users.add(user)
        } else {
            this.users[index] = user
        }
    }

    fun getMaxId(): Int {
        val initId = 1
        return this.users.maxByOrNull { it.id ?: initId }?.id ?: initId
    }
}