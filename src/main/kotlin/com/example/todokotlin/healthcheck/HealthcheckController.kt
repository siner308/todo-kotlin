package com.example.todokotlin.healthcheck

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/healthcheck")
class HealthcheckController {
    @GetMapping()
    fun healthcheck(): String {
        return "OK"
    }
}