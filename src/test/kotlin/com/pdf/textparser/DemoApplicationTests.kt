package com.pdf.textparser

import org.apache.pdfbox.pdmodel.PDDocument
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import kotlin.text.forEach

@RunWith(SpringRunner::class)
@SpringBootTest
class DemoApplicationTests {

    @Test
    fun loadPdfDocumentTest() {
        val pdfDocument = pdDocument()
        pdfDocument.use {
            Assert.assertEquals(108, pdfDocument?.numberOfPages)
        }

    }

    @Test
    fun readTextFromPdfDocumentTest() {
       val regexp = "[A-Za-z]+".toRegex()


        val pdfDocument = pdDocument()
        pdfDocument.use {
          val pdfText = readPdfDocumentText(pdfDocument)
          println("Letters of file ZeroMQ =  ${pdfText.length}")
        //  println("Length of file ZeroMQ =  ${pdfText.slice(IntRange(0,15000))}")
        //  println("Length of file ZeroMQ =  ${pdfText.filter({s:Char -> s.isLetter().and()})}")
          val listOfWords: Sequence<MatchResult> = regexp.findAll(pdfText)
          println(listOfWords.count())
          println(listOfWords.toList().forEach({println(it.value)}))
            //  val words: MatchResult? = regexp.find(pdfText)
        //  println("Words in ZeroMQ = ${listOfWords.size}")
          // print(words?.next().toString())
            listOfWords.onEach { print(it.groupValues) }

        }
    }

    private fun pdDocument(): PDDocument? {
        val pdfDocument = readPdfFile(getHomePath("ZeroMQ.pdf"))
        return pdfDocument
    }


}
