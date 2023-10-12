package com.example.todokotlin.user

import com.example.todokotlin.todo.Todo
import jakarta.persistence.*

@Entity(name = "user")
class User(
    @Id
    @GeneratedValue()
    var id: Long = 0,

    @OneToMany(mappedBy = "user")
    var todos: List<Todo>? = null,
)