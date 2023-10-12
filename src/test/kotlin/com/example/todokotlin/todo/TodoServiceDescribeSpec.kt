package com.example.todokotlin.todo.tests

import com.example.todokotlin.todo.*
import com.example.todokotlin.user.User
import com.example.todokotlin.user.UserRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TodoServiceDescribeSpec @Autowired constructor(
    val todoRepository: TodoRepository,
    val userRepository: UserRepository,
) : DescribeSpec({
    val todoService = TodoService(todoRepository)

    describe("TodoService") {
        val user = User()

        beforeAny {
            userRepository.save(user)
        }
        beforeEach {
            todoRepository.deleteAll()
        }

        describe("getTodos") {
            it("should return empty todos") {
                todoService.getTodos() shouldBe emptyList()
            }

            it("should return 3 todos") {
                todoRepository.saveAll(
                    listOf(
                        Todo(title = "1", user = user),
                        Todo(title = "2", user = user),
                        Todo(title = "3", user = user),
                    )
                )
                todoService.getTodos().size shouldBe 3
            }
        }

        describe("getByIdOrFail") {
            it("should return todo with given id") {
                val todo = Todo(title = "4", user = user)
                todoRepository.save(todo)
                todoService.getByIdOrFail(todo.id).id shouldBe todo.id
            }

            it("should throw exception when todo with given id does not exist") {
                val invalidId = 99999999L
                val exception = shouldThrow<Exception> {
                    todoService.getByIdOrFail(invalidId)
                }
                exception.message shouldBe "Todo:$invalidId not found"
            }
        }
        describe("getByIds") {
            val todos = listOf(
                Todo(title = "getByIds title 1", user = user),
                Todo(title = "getByIds title 2", user = user),
                Todo(title = "getByIds title 3", user = user),
            )

            beforeAny {
                todoRepository.saveAll(todos)
            }

            it("should return todos with given ids") {
                todoService.getByIds(
                    listOf(
                        todos[0].id,
                        todos[2].id,
                    ),
                ).map { it.id } shouldBe listOf(
                    todos[0].id,
                    todos[2].id,
                )
            }

            it("should return empty list when todos with given ids do not exist") {
                todoService.getByIds(listOf(4, 5)) shouldBe emptyList()
            }
        }
        describe("create") {
            it("should create todo") {
                val title = "title"
                val description = "description"

                val todo = todoService.create(
                    dto = CreateTodoDTO(
                        title = title,
                        description = description,
                    ),
                    user = user,
                )

                todo.title shouldBe title
                todo.description shouldBe description
                todo.user shouldBe user
            }
        }
        describe("update") {
            val todo = Todo(
                title = "update title",
                user = user
            )

            beforeAny {
                todoRepository.save(todo)
            }

            it("should update todo") {
                val updatedTodo = todoService.update(
                    todo = UpdateTodoDTO(
                        id = todo.id,
                        done = true,
                    ),
                    user = user,
                )

                updatedTodo.done shouldBe true
            }

            it("should throw exception when todo does not belong to user") {
                val otherUser = User()
                userRepository.save(otherUser)

                val exception = shouldThrow<Exception> {
                    todoService.update(
                        todo = UpdateTodoDTO(
                            id = todo.id,
                            done = true,
                        ),
                        user = otherUser,
                    )
                }
                exception.message shouldBe "Todo:${todo.id} does not belong to user:${otherUser.id}"
            }
        }
        describe("delete(id)") {
            val todos = listOf(
                Todo(title = "delete title 1", user = user),
                Todo(title = "delete title 2", user = user),
                Todo(title = "delete title 2", user = user),
            )

            beforeEach {
                todoRepository.saveAll(todos)
            }

            it("should delete todo") {
                todoService.delete(todos[1].id)
                todoService.getTodos().map { it.id } shouldBe listOf(todos[0].id, todos[2].id)
            }
        }
        describe("delete (collection)") {
            val todos = listOf(
                Todo(title = "delete collection 1", user = user),
                Todo(title = "delete collection 2", user = user),
                Todo(title = "delete collection 3", user = user),
            )
            beforeEach {
                todoRepository.saveAll(todos)
            }

            it("should delete todos") {
                todoService.delete(listOf(todos[0].id, todos[2].id))
                todoService.getTodos().map { it.id } shouldBe listOf(todos[1].id)
            }
        }
    }
})
