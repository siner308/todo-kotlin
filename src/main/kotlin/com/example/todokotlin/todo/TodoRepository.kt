package com.example.todokotlin.todo

import org.springframework.stereotype.Repository

@Repository
class TodoRepository {
    private val todos = mutableListOf<Todo>()

    fun getTodos(): List<Todo> {
        return this.todos
    }

    fun getByIdOrNull(id: Int): Todo? {
        return todos.firstOrNull { it.id == id }
    }

    fun save(todo: Todo) {
        val index = this.todos.indexOfFirst { it.id == todo.id }
        if (index == -1) {
            todo.id = this.getMaxId() + 1
            this.todos.add(todo)
        } else {
            this.todos[index] = todo
        }
    }

    fun delete(todo: Todo): Unit {
        this.todos.removeIf { it.id == todo.id }
    }

    fun delete(todos: Collection<Todo>): Unit {
        val ids = todos.map { it.id }
        this.todos.removeIf { it.id in ids  }
    }

    private fun getMaxId(): Int {
        val initId = 1
        return this.todos.maxByOrNull { it.id ?: initId }?.id ?: initId
    }

    fun getByIds(ids: Collection<Int>): Collection<Todo> {
        return this.todos.filter { it.id in ids }
    }
}