package com.example.webfluxtutorial.router

import com.example.webfluxtutorial.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions

@Configuration
class RouterConfig {
    @Bean
    fun routes(userHandler: UserHandler): RouterFunction<*> {
        return RouterFunctions.route().GET("/handler/users") { userHandler.getUsers(it)}.build()
    }
}