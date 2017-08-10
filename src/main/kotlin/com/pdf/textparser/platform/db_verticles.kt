package com.pdf.textparser.platform

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.eventbus.EventBus


class DbVerticle : AbstractVerticle() {


    override fun start(startFuture: Future<Void>?) {
        super.start(startFuture)
        val eventBus: EventBus = vertx.eventBus()

    }
}