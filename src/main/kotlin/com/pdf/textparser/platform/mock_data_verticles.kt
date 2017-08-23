package com.pdf.textparser.platform

import com.pdf.textparser.businesslogic.*
import com.pdf.textparser.consts.WebVar
import com.pdf.textparser.consts.WebVar.*
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier


class MockDataVerticle : AbstractVerticle() {


    companion object {
        val text: String = readPdfDocumentText(readPdfFile(getHomePath("Kotlin_in_Action.pdf")))

    }

    override fun start(startFuture: Future<Void>?) {
        super.start(startFuture)
        val eb = vertx.eventBus()

        /**
         * Параметризуем типом который получаем
         */
        eb.consumer<JsonObject>(MOCK_DATA_ADDRESS.param,
                { message ->
                    val messageBody = message.body()
                  //  val bookName = messageBody.getString("book_name")
                    val requestType = messageBody.getString(REQUEST_TYPE.param)
                    val bookStatistic: BookStatistic = BookStatistic(text, Book("qqq"))
                    when (requestType) {
                        "all-tokens" -> message.reply(JsonObject().put("list", bookStatistic.sortAscendingTokensMap()))
                        "statistics" -> message.reply(JsonObject().put("map", bookStatistic.sortDescendingTokensMap()))
                    }

                })


    }

    @Qualifier("simpleTextOperations")
    @Autowired lateinit var textOperations: SimpleTextOperations
}