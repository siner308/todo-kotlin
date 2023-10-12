package com.example.todokotlin.todo

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

class CreateTodoDTO(
    @NotBlank
    @NotNull
    var title: String,

    @NotBlank
    var description: String?,
)

class UpdateTodoDTO(
    @NotNull
    @Positive
    val id: Long,

    @NotNull
    val done: Boolean,
)
