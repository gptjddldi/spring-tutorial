package com.tutorial.webfluxsecuritytutorial

import com.tutorial.webfluxsecuritytutorial.user.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions

@Configuration
class RouterConfig {
    @Bean
    fun routes(userHandler: UserHandler): RouterFunction<*> {
        return RouterFunctions.route()
            .GET("/users") { userHandler.getUsers(it)}
            .POST("/register") { userHandler.createUser(it)}
            .build()
    }
}