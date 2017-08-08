package com.pdf.textparser.platform

import com.pdf.textparser.businesslogic.Word
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.core.http.HttpServer
import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import io.vertx.core.logging.LoggerFactory
import io.vertx.ext.web.Router


class ApiVerticle : AbstractVerticle() {

    val log = LoggerFactory.getLogger(ApiVerticle::class.java)

    override fun start(startFuture: Future<Void>?) {
        super.start(startFuture)
        val apiServer: HttpServer = vertx.createHttpServer()
        val router: Router = Router.router(vertx)
        router.get().path("/word").handler({ context ->
            context.response().putHeader("content-type", "application/json;charset=utf-8")
            context.response().end(Json.encodePrettily(listOf(Word("hello", setOf("привет")))))
        })

        apiServer.requestHandler(router::accept).listen(8081, {asyncResult -> println(asyncResult.succeeded()) })

    }
}