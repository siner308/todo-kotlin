package com.example.todokotlin.todo

import com.example.todokotlin.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/todos")
class TodoController @Autowired constructor(private val todoService: TodoService) {

    @GetMapping()
    fun getTodos(): List<Todo> {
        return this.todoService.getTodos()
    }

    @GetMapping("/{id}")
    fun getTodo(@PathVariable id: Int): Todo {
        return this.todoService.getByIdOrFail(id)
    }

    @PostMapping()
    fun createTodo(todo: CreateTodoDTO, user: User = User(1)): Todo {
        return this.todoService.create(todo, user)
    }

    fun updateTodo(todo: UpdateTodoDTO, user: User = User(1)): Todo {
        return this.todoService.update(todo, user)
    }

    @DeleteMapping("/{id}")
    fun removeTodo(@PathVariable id: Int): Unit {
        this.todoService.delete(id)
    }
}