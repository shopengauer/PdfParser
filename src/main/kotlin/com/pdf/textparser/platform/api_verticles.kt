package com.pdf.textparser.platform

import com.pdf.textparser.consts.WebVar
import com.pdf.textparser.consts.WebVar.*
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.eventbus.DeliveryOptions
import io.vertx.core.eventbus.EventBus
import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import io.vertx.core.logging.LoggerFactory
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.handler.StaticHandler


class ApiVerticle : AbstractVerticle() {

    val log = LoggerFactory.getLogger(ApiVerticle::class.java)

    override fun start(startFuture: Future<Void>?) {
        super.start(startFuture)

        /**
         * Create EventBus instance
         */
        val eb: EventBus = vertx.eventBus()

        /**
         * Create Router instance
         */
        val router: Router = Router.router(vertx)

        /**
         * Route for static resources
         */
        router.route(STATIC_ROOT.param).handler(StaticHandler.create().setCachingEnabled(false))

        /**
         *  Route for uploading files
         */
        router.route().handler(BodyHandler.create().setUploadsDirectory(FILE_UPLOAD_DIR.param))


        router.get().path("/${ROOT_API_PATH.param}/:${BOOK_NAME_PARAM.param}/:${REQUEST_TYPE.param}").handler({ context ->
            context.response().putHeader("content-type", "application/json;charset=utf-8")

            val request = context.request()

            val requestMessage = JsonObject().put("book_name", request.getParam(BOOK_NAME_PARAM.param)).
                    put("request_type", request.getParam(REQUEST_TYPE.param))

            /**
             * Параметризуем send типом который должен вернуться
             */
            eb.send<JsonObject>("mock_data", requestMessage,
                    { asyncResult ->
                        context.response().
                                // encode Json to String
                                end(Json.encodePrettily(asyncResult.result().body()))
                    })

        })

        router.post().path("/fileupload").handler({ ctx ->
            ctx.response().putHeader("Content-Type", "text/plain")
            ctx.response().isChunked = true
            for (f in ctx.fileUploads()) {

                ctx.response().write("File name: ${f.fileName()}")
            }
            ctx.response().end()
        })

        vertx.createHttpServer().requestHandler(router::accept).listen(8080, { asyncResult -> println(asyncResult.succeeded()) })

    }
}