package com.pdf.textparser.platform

import com.pdf.textparser.businesslogic.*
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.json.Json
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier


class MockDataVerticle : AbstractVerticle() {


    companion object {
        val text = readPdfDocumentText(readPdfFile(getHomePath("Kotlin_in_Action.pdf")))

    }

    override fun start(startFuture: Future<Void>?) {
        super.start(startFuture)
        val eb = vertx.eventBus()

        eb.consumer<List<Book>>("mockdata",
                { message ->
                    val headers = message.headers()
                    when(headers.get("action")){
                        "all-tokens" -> message.reply(Json.encodePrettily(textOperations.listOfTextTokens(text)))
                        "statistics" -> message.reply(Json.encodePrettily(BookStatistic(textOperations.listOfTextTokens(text)).sortDescendingTokensMap()))
                    }

                })
    }

    @Qualifier("simpleTextOperations") @Autowired lateinit var textOperations: SimpleTextOperations
}