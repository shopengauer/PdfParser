package com.pdf.textparser.platform

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.eventbus.DeliveryOptions
import io.vertx.core.http.HttpServer
import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import io.vertx.core.logging.LoggerFactory
import io.vertx.ext.web.Router


class ApiVerticle : AbstractVerticle() {

    val log = LoggerFactory.getLogger(ApiVerticle::class.java)

    override fun start(startFuture: Future<Void>?) {
        super.start(startFuture)
        val eb = vertx.eventBus()

        val apiServer: HttpServer = vertx.createHttpServer()
        val router: Router = Router.router(vertx)

        router.get().path("/word/:book_name/:request_type").handler({ context ->
            context.response().putHeader("content-type", "application/json;charset=utf-8")

            val bookName = context.request().getParam("book_name")
            val requestType = context.request().getParam("request_type")
            val requestMessage = JsonObject().put("book_name", bookName).
                    put("request_type", requestType)
            val deliveryOptions = DeliveryOptions().addHeader("action", requestType)
            /**
             * Параметризуем send типом который должен вернуться
             */
            eb.send<JsonObject>("mock_data", requestMessage, deliveryOptions,
                    { asyncResult ->
                                context.response().
                                // encode Json to String
                                end(Json.encodePrettily(asyncResult.result().body()))
                    })

        })

        apiServer.requestHandler(router::accept).listen(8081, { asyncResult -> println(asyncResult.succeeded()) })

    }
}