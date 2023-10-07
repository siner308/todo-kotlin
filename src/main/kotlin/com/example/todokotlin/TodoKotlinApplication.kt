package com.example.todokotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class TodoKotlinApplication


fun main(args: Array<String>) {
    SpringApplication.run(TodoKotlinApplication::class.java, *args)
}
