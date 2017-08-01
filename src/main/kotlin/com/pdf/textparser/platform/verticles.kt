package com.pdf.textparser.platform

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


class ServerVerticle : AbstractVerticle(){

    override fun start(startFuture: Future<Void>?) {
        super.start(startFuture)

        var server = vertx.createHttpServer().apply {

            requestHandler({ request ->

                // This handler gets called for each request that arrives on the server
                var response = request.response()
                response.putHeader("content-type", "text/plain")

                // Write to the response and end it
                response.end("This is my startUp PdfWords!")
            })

            listen(8081)
        }
    }
}

open class StaticVerticle : AbstractVerticle(){

    override fun start() {
        super.start()
        val router = Router.router(vertx)
        router.route().handler(StaticHandler.create().setWebRoot("webroot/web/"))
        vertx.createHttpServer().requestHandler{httpServerRequest -> router.accept(httpServerRequest)}.listen(8080)
    }
}

@Configuration
class VerticleConfiguration{

    @Bean
    open fun init() = CommandLineRunner {
        val vertx: Vertx = Vertx.vertx()
        vertx.deployVerticle(serverVerticle())
        vertx.deployVerticle(staticVerticle())
    }


    @Bean
    fun serverVerticle() = ServerVerticle()

    @Bean
    fun staticVerticle() = StaticVerticle()

}
