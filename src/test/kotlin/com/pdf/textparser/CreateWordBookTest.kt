package com.pdf.textparser

import com.pdf.domain.Book
import com.pdf.domain.BookWordStatistics
import com.pdf.domain.Word
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class CreateWordBookTest {





    private val testWord1 = "Hello"
    private val testWordTranslate1 = "Привет"
    private val testWordTranslate2 = "Здорово"
    private val bookAuthor1 = "Dmitry Jemerov"
    private val bookAuthor2 = "Svetlana Isakova"
    private val bookTitle = "Kotlin in Action"
    private val bookIsbn = "9781617293290"

    private val testText = """At the same time, because Kotlin is fully compatible with Java 6, its use doesn’t
        introduce any new compatibility concerns. You’ll benefit from all the cool new lan-
        guage features of Kotlin, and your users will still be able to run your application on
        their devices, even if they don’t run the latest version of Android.
        In terms of performance, using Kotlin doesn’t bring any disadvantages, either. The
        code generated by the Kotlin compiler is executed as efficiently as regular Java code.
        The runtime used by Kotlin is fairly small, so you won’t experience a large increase in
        the size of the compiled application package. And when you use lambdas, many of the
        Kotlin standard library functions will inline them. Inlining lambdas ensures that no
        new objects will be created and the application won’t suffer from extra GC pauses.
        Having looked at the advantages of Kotlin compared to Java, let’s now look at Kot-
        lin’s philosophy—the main characteristics that distinguish Kotlin from other modern
        languages targeting the JVM.
        """




    @Test
    fun createEnWordTest() {

        val book = Book(bookTitle, setOf(bookAuthor1, bookAuthor2), bookIsbn)
        Assert.assertEquals(bookTitle, book.title)
        Assert.assertTrue(book.authors.contains(bookAuthor1) && book.authors.contains(bookAuthor2))
        Assert.assertEquals(bookIsbn, book.isbn)

        val word: Word = Word(testWord1).addBook(book)
        val wordPlus = word.addTranslate(testWordTranslate1).addTranslate(testWordTranslate2)
        Assert.assertEquals(testWord1, wordPlus.word)
        Assert.assertTrue(wordPlus.translates.contains(testWordTranslate1))
        Assert.assertTrue(wordPlus.translates.contains(testWordTranslate2))

        Assert.assertTrue(word.books.contains(book))

        val matchResults: List<MatchResult> = simpleTextOperations.getListOfTokens(testText)
        val listOfTokens : List<String> = simpleTextOperations.filterListOfTokens(matchResults)
        println(listOfTokens)

        val bookWordStatistic = BookWordStatistics(listOfTokens,book)

        println(bookWordStatistic.getListOfBookWords)
        println(bookWordStatistic.getSetOfBookWords)
        println(bookWordStatistic.mapOfBookWordOccurs)
        println(bookWordStatistic.sortDescendingTokensMap(bookWordStatistic.mapOfBookTokenOccurs))
        println(bookWordStatistic.sortAccendingTokensMap(bookWordStatistic.mapOfBookTokenOccurs))
        println(bookWordStatistic.getWordsStatistics(bookWordStatistic.mapOfBookTokenOccurs))
        for((occurs,numberOfTokens) in bookWordStatistic.getWordsStatistics(bookWordStatistic.mapOfBookTokenOccurs))
            println("$numberOfTokens ${if(numberOfTokens > 1) "words" else "word"}, text contain $occurs time ")




    }

    @Test
    fun deconstructStringTest() {


    }

    @Autowired lateinit var simpleTextOperations: TextOperations


}