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
import java.util.function.Supplier


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

    /**
     * Огромное значение имеет поряток настройки путейю
     * Настраивать нужно от частных к общим в последнюю очередь "/"
     * обработчика статического контента
     */
    private fun createRouter(): Router = Router.router(vertx).apply {

       // route().handler(BodyHandler.create().setUploadsDirectory(FILE_UPLOAD_DIR.param))
        //  post().path(FILE_UPLOAD_PATH.param).handler(fileUploadHandler)
        get().path("${ROOT_API_PATH.param}/:${BOOK_NAME_PARAM.param}/:${REQUEST_TYPE.param}").handler(bookStatisticHandler)
        get().path("/dog").handler(testHandler)
        route().handler(StaticHandler.create().setWebRoot("webroot/web/").setCachingEnabled(false))

    }

    private val fileUploadHandler = Handler<RoutingContext> { ctx: RoutingContext ->
        ctx.response().putHeader("Content-Type", "text/plain")
        ctx.response().isChunked = true
        for (f in ctx.fileUploads()) {

            ctx.response().write("File name: ${f.fileName()}")
        }
        ctx.response().end()
    }

    private val bookStatisticHandler: Handler<RoutingContext> = Handler { context: RoutingContext ->
        context.response().putHeader("content-type", "application/json;charset=utf-8")
        eb.send<JsonObject>(MOCK_DATA_ADDRESS.param, JsonObject()
                .put(BOOK_NAME_PARAM.param, context.request().getParam(BOOK_NAME_PARAM.param))
                .put(REQUEST_TYPE.param, context.request().getParam(REQUEST_TYPE.param)),
                { asyncResult -> context.response().end(Json.encodePrettily(asyncResult.result().body())) }
        )
    }

    private val testHandler: Handler<RoutingContext> = Handler { context: RoutingContext ->
        context.response().putHeader("content-type", "application/json;charset=utf-8")
        context.response().end(Json.encodePrettily(Dog()))
        //context.response().end(Dog().toString())

    }

    private val supplier: Supplier<String> = Supplier { "asas" }

    private lateinit var eb:EventBus
}

data class Dog(val name:String = "Suslic", val breed:String = "Gonchaya")




