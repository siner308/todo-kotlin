package com.example.todokotlin.todo

import com.example.todokotlin.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoService(
    @Autowired private val todoRepository: TodoRepository
) {
    fun getTodos(): List<Todo> {
        return this.todoRepository.findAll()
    }

    fun getByIdOrFail(id: Long): Todo {
        return this.todoRepository.findByIdOrNull(id) ?: throw Exception("Todo:${id} not found")
    }

    fun getByIds(ids: Iterable<Long>): Iterable<Todo> {
        return this.todoRepository.findAllById(ids)
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
        if (todoToUpdate.user.id != user.id) {
            throw Exception("Todo:${todo.id} does not belong to user:${user.id}")
        }
        todoToUpdate.done = todo.done
        this.todoRepository.save(todoToUpdate)
        return todoToUpdate
    }

    fun delete(id: Long): Unit {
        val todo = this.getByIdOrFail(id)
        this.todoRepository.delete(todo)
    }

    fun delete(ids: Iterable<Long>): Unit {
        val todos = this.getByIds(ids)
        this.todoRepository.deleteAllInBatch(todos)
    }
}