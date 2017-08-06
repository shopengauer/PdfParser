package com.pdf.textparser.platform

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.http.HttpServer
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
            context.response().putHeader("content-type", "application/json")
            context.response().end(JsonObject().put("book", "Kotlin in action")
                    .put("words", JsonObject().put("emerge","появляться").put("study","учиться"))
                    .encodePrettily())
        })

        apiServer.requestHandler { router.accept(it) }.listen(8081)

    }
}