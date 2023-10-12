package com.example.todokotlin.todo

import com.example.todokotlin.user.User
import jakarta.persistence.*


@Entity(name = "todo")
class Todo(
    @Id
    @GeneratedValue()
    var id: Long = 0,

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "description", columnDefinition = "TEXT")
    var description: String? = null,

    @Column(nullable = false)
    var done: Boolean = false,

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,
)