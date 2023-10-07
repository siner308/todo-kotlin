package com.example.todokotlin.todo

import com.example.todokotlin.user.User


data class Todo(
    var title: String? = null,
    var description: String? = null,
    var done: Boolean? = false,
    var user: User? = null,
) {
    var id: Int? = null
}
