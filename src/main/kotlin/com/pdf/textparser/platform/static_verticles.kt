package com.pdf.textparser.platform

import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler

class StaticVerticle : AbstractVerticle() {

    override fun start() {
        super.start()
        val router = Router.router(vertx)
        router.route().handler(StaticHandler.create().setCachingEnabled(false).setWebRoot("webroot/web/"))
        vertx.createHttpServer().requestHandler { httpServerRequest -> router.accept(httpServerRequest) }.listen(8080)
    }
}
