package com.example.todokotlin.todo.tests

import com.example.todokotlin.todo.Todo
import com.example.todokotlin.todo.TodoRepository
import com.example.todokotlin.todo.TodoService
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe


internal class TodoServiceDescribeSpec : DescribeSpec({
    val todoRepository = TodoRepository()
    val todoService = TodoService(todoRepository = todoRepository)

    beforeEach {
        todoRepository.delete(
            todoRepository.getTodos(),
        )
    }

    describe("TodoService") {
        describe("getTodos") {
            describe("should return empty todos") {
                it("success") {
                    todoService.getTodos() shouldBe emptyList()
                }
            }

            describe("should return 3 todos") {
                beforeEach {
                    todoRepository.save(Todo())
                    todoRepository.save(Todo())
                    todoRepository.save(Todo())
                }

                it("success") {
                    todoService.getTodos().size shouldBe 3
                }
            }
        }
        describe("getByIdOrFail") {
            it("should return todo with given id") {

            }

            it("should throw exception when todo with given id does not exist") {

            }
        }
        describe("getByIds") {
            it("should return todos with given ids") {

            }
        }
        describe("create") {
            it("should create todo") {

            }
        }
        describe("update") {
            it("should update todo") {

            }
        }
        describe("delete") {
            it("should delete todo") {

            }
        }
        describe("delete") {
            it("should delete todos") {

            }
        }
    }
})
