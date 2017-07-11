package com.pdf.textparser

import com.pdf.domain.Book
import com.pdf.domain.Word
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
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

    }

    @Test
    fun deconstructStringTest() {

    }


}