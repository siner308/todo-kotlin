package com.example.todokotlin.todo

import com.example.todokotlin.user.User
import org.springframework.stereotype.Service

@Service
class TodoService constructor(private val todoRepository: TodoRepository) {
    fun getTodos(): List<Todo> {
        return this.todoRepository.getTodos()
    }

    fun getByIdOrFail(id: Int): Todo {
        return this.todoRepository.getByIdOrNull(id) ?: throw Exception("Todo not found")
    }

    fun getByIds(ids: Collection<Int>): Collection<Todo> {
        return this.todoRepository.getByIds(ids)
    }

    fun create(dto: CreateTodoDTO, user: User): Todo {
        val todo = Todo(
            title = dto.title,
            description = dto.description,
            done = false,
            user = user,
        )
        this.todoRepository.save(todo)
        return todo
    }

    fun update(todo: UpdateTodoDTO, user: User): Todo {
        val todoToUpdate = this.getByIdOrFail(todo.id)
        todoToUpdate.done = todo.done
        this.todoRepository.save(todoToUpdate)
        return todoToUpdate
    }

    fun delete(id: Int): Unit {
        val todo = this.getByIdOrFail(id)
        this.todoRepository.delete(todo)
    }

    fun delete(ids: Collection<Int>): Unit {
        val todos = this.getByIds(ids)
        this.todoRepository.delete(todos)
    }
}