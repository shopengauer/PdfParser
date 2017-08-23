package com.pdf.textparser.platform

import com.pdf.textparser.consts.WebVar.*
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.core.eventbus.EventBus
import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import io.vertx.core.logging.LoggerFactory
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.handler.StaticHandler


class ApiVerticle : AbstractVerticle() {

    val log = LoggerFactory.getLogger(ApiVerticle::class.java)

    override fun start(startFuture: Future<Void>?) {
        super.start(startFuture)
        eb = vertx.eventBus()
        val router: Router = createRouter()
        vertx.createHttpServer().
                requestHandler(router::accept).
                listen(8080, { asyncResult -> println(asyncResult.succeeded()) })

    }

    private fun createRouter(): Router = Router.router(vertx).apply {
        route().handler(BodyHandler.create().setUploadsDirectory(FILE_UPLOAD_DIR.param))
        route(STATIC_ROOT.param).handler(StaticHandler.create().setCachingEnabled(false))
        post().path(FILE_UPLOAD_PATH.param).handler(fileUploadHandler)
        get().path("${ROOT_API_PATH.param}/:${BOOK_NAME_PARAM.param}/:${REQUEST_TYPE.param}").handler(bookStatisticHandler)
    }

    private val fileUploadHandler = Handler<RoutingContext> { ctx: RoutingContext ->
        ctx.response().putHeader("Content-Type", "text/plain")
        ctx.response().isChunked = true
        for (f in ctx.fileUploads()) {

            ctx.response().write("File name: ${f.fileName()}")
        }
        ctx.response().end()
    }

    private val bookStatisticHandler: Handler<RoutingContext> = Handler<RoutingContext> { context: RoutingContext ->
        context.response().putHeader("content-type", "application/json;charset=utf-8")
        eb.send<JsonObject>(MOCK_DATA_ADDRESS.param, JsonObject()
                .put(BOOK_NAME_PARAM.param, context.request().getParam(BOOK_NAME_PARAM.param))
                .put(REQUEST_TYPE.param, context.request().getParam(REQUEST_TYPE.param)),
                { asyncResult -> context.response().end(Json.encodePrettily(asyncResult.result().body())) }
        )
    }


    internal lateinit var eb:EventBus
}
