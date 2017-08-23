package com.pdf.textparser.platform

import io.vertx.core.Vertx
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class VerticleConfiguration {


    @Bean
    fun init() = CommandLineRunner {
        val vertx: Vertx = Vertx.vertx()
        vertx.deployVerticle(apiVerticle())
        vertx.deployVerticle(mockDataVerticle())
    }

    @Bean
    fun apiVerticle() = ApiVerticle()

    @Bean
    fun mockDataVerticle() = MockDataVerticle()




}
