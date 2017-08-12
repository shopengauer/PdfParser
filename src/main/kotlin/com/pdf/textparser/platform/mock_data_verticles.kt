package com.pdf.textparser.platform

import com.pdf.textparser.businesslogic.*
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.json.JsonObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier


class MockDataVerticle : AbstractVerticle() {


    companion object {
        val text:String = readPdfDocumentText(readPdfFile(getHomePath("Kotlin_in_Action.pdf")))

    }

    override fun start(startFuture: Future<Void>?) {
        super.start(startFuture)
        val eb = vertx.eventBus()

        /**
         * Параметризуем типом который возвращаем
         */
        eb.consumer<JsonObject>("mock_data",
                { message ->
                    val messageBody = message.body()
                    val bookName = messageBody.getString("book_name")
                    val requestType = messageBody.getString("request_type")
                    val bookStatistic:BookStatistic = BookStatistic(text,Book(bookName))
                    when(requestType){
                        "all-tokens" -> message.reply(JsonObject().put("list",bookStatistic.wordsStatistics))
                        "statistics" -> message.reply(JsonObject().put("map",bookStatistic.sortDescendingTokensMap()))
                    }

                })
    }

    @Qualifier("simpleTextOperations") @Autowired lateinit var textOperations: SimpleTextOperations
}