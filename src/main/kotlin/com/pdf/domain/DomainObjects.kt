package com.pdf.domain

import org.slf4j.Logger
import org.slf4j.LoggerFactory


data class Book(val title: String, val authors: Set<String> = setOf(), var isbn: String = "")

data class Word(val word: String, val translates: Set<String> = setOf(), val books: Set<Book> = setOf()) {
    fun addTranslate(translate: String) = Word(this.word, translates.plusElement(translate), this.books)
    fun addBook(book: Book) = Word(this.word, this.translates, books.plusElement(book))
}

class BookWordStatistics(val listOfBookStringTokens: List<String>, val book:Book) {

    val logger: Logger = LoggerFactory.getLogger("com.pdf.domain.BookWordStatistics")

    val setOfBookStringTokens: Set<String> get() {
        return listOfBookStringTokens.toSet()
    }

    val setOfBookWords: Set<Word> get() {
        return listOfBookStringTokens.map { Word(it) }.toHashSet()
    }

    val listOfBookWords: List<Word> get() {
        return listOfBookStringTokens.map { Word(it) }
    }

    val mapOfBookWordOccurs: Map<Word, Int> get() {
        val mapOfBookWordOccurs = hashMapOf<Word, Int>()
        listOfBookWords.forEach {
            mapOfBookWordOccurs.put(it, (mapOfBookWordOccurs[it]!!.plus(1)))
        }
        return mapOfBookWordOccurs
    }


}



