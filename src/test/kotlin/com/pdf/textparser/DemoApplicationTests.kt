package com.pdf.textparser

import org.apache.pdfbox.pdmodel.PDDocument
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.Comparator
import java.util.function.ToIntFunction

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
            //   println("Number of Letters of file ZeroMQ =  ${pdfText.length}")
            //   println("Letters of file ZeroMQ =  ${pdfText.decapitalize()}")

            val wordCountMap = hashMapOf<String, Int>()

            val listOfWords = regexp.findAll(pdfText).toList()

            val processWords = listOfWords
                    .map { it.value }
                    .map {
                        when {
                            it.length == 1 -> ""
                            it[0].isUpperCase() && it[1].isUpperCase() -> it
                            else -> it.toLowerCase()
                        }
                    }.filter { it != "" }


            processWords.forEach {
                if (!wordCountMap.containsKey(it))
                    wordCountMap.put(it, 1)
                else
                    wordCountMap.put(it, (wordCountMap[it]!! + 1))
            }

            println(wordCountMap.values.sortedDescending())
            val sortedCountWords = wordCountMap.values.sortedDescending()

            println(wordCountMap.entries.sortedWith(Comparator.comparingInt({-it.value})))


            val setOfWords = processWords.toSet()





            println(setOfWords.size)
            println(processWords.size)


            // println(if (word.value.toList()[1].isUpperCase()) word.value else word.value.decapitalize())

            //println(listOfWords.toList().forEach({println(it.value)}))
            //  val words: MatchResult? = regexp.find(pdfText)
            //  println("Words in ZeroMQ = ${listOfWords.size}")
            // print(words?.next().toString())


            //   listOfWords.onEach { print(it.groupValues) }

        }
    }

    private fun pdDocument(): PDDocument? {
        val pdfDocument = readPdfFile(getHomePath("ZeroMQ.pdf"))
        return pdfDocument
    }


}
