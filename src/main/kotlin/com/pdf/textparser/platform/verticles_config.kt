package com.pdf.textparser.platform

import io.vertx.core.Vertx
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class VerticleConfiguration {

    @Bean
    open fun init() = CommandLineRunner {
        val vertx: Vertx = Vertx.vertx()
        vertx.deployVerticle(apiVerticle())
        vertx.deployVerticle(staticVerticle())
    }


    @Bean
    fun apiVerticle() = ApiVerticle()

    @Bean
    fun staticVerticle() = StaticVerticle()

}
