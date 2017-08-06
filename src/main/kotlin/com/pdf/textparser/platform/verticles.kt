package com.pdf.textparser.platform

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.Vertx
import io.vertx.core.http.HttpServer
import io.vertx.core.json.JsonObject
import io.vertx.core.logging.LoggerFactory
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


class ApiVerticle : AbstractVerticle() {

    val log = LoggerFactory.getLogger(ApiVerticle::class.java)

    override fun start(startFuture: Future<Void>?) {
        super.start(startFuture)
        val apiServer: HttpServer = vertx.createHttpServer()
        val router: Router = Router.router(vertx)
        router.get().path("/word").handler({ context ->
            context.response().putHeader("content-type", "application/json")
            context.response().end(JsonObject().put("book", "Kotlin in action")
                    .put("words",JsonObject().put("emerge","появляться").put("study","учиться"))
                    .encodePrettily())
        })

        apiServer.requestHandler { router.accept(it) }.listen(8081)

    }
}

class StaticVerticle : AbstractVerticle() {

    override fun start() {
        super.start()
        val router = Router.router(vertx)
        router.route().handler(StaticHandler.create().setCachingEnabled(false).setWebRoot("webroot/web/"))
        vertx.createHttpServer().requestHandler { httpServerRequest -> router.accept(httpServerRequest) }.listen(8080)
    }
}

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
