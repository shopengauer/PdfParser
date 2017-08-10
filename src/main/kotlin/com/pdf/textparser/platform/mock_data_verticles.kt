package com.pdf.textparser.platform

import com.pdf.textparser.businesslogic.*
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.json.Json
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service


class MockDataVerticle : AbstractVerticle() {


    companion object {
        val text = readPdfDocumentText(readPdfFile(getHomePath("Kotlin_in_Action.pdf")))

    }

    override fun start(startFuture: Future<Void>?) {
        super.start(startFuture)
        val eb = vertx.eventBus()

        eb.consumer<List<Book>>("mockdata", { message -> message.reply(Json.encodePrettily(textOperations.listOfTextTokens(text))) })
    }

    @Qualifier("simpleTextOperations") @Autowired lateinit var textOperations: SimpleTextOperations
}