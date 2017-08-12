package com.pdf.textparser

import com.pdf.textparser.businesslogic.*
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class ReadPdfFileTest {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(ReadPdfFileTest::class.java)
    }

    @Qualifier("simpleTextOperations")
    @Autowired lateinit var simpleTextOperations: TextOperations


    @Test
    fun userHomeDirTest() {
        logger.info("User home directory: {}", getHomePath("Kotlin_in_Action.pdf"))
        val text = readPdfDocumentText(readPdfFile(getHomePath("Kotlin_in_Action.pdf")))

      val matchResults = simpleTextOperations.getListOfTokens(text)
      val listOfTokens = simpleTextOperations.filterListOfTokens(matchResults)

      val bookStatistics = BookStatistic(text)
        println(bookStatistics.numberOfTokens)
        println(bookStatistics.numberOfWords)
        println(bookStatistics.wordsSet)
        println(bookStatistics.sortDescendingTokensMap())
        println(bookStatistics.wordsStatistics)

    }
}