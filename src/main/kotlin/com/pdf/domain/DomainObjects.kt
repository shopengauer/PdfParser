package com.pdf.domain

import org.slf4j.Logger
import org.slf4j.LoggerFactory

val createWord = { tokens: String, book : Book -> Word(tokens, setOf(), setOf(book))}

data class Book(val title: String, val authors: Set<String> = setOf(), var isbn: String = "")

data class Word(val word: String, val translates: Set<String> = setOf(), val books: Set<Book> = setOf()) {
    fun addTranslate(translate: String) = Word(this.word, translates.plusElement(translate), this.books)
    fun addBook(book: Book) = Word(this.word, this.translates, books.plusElement(book))
}

class BookWordStatistics(val listOfBookStringTokens: List<String>, val book: Book) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(BookWordStatistics::class.java)
    }


    val getSetOfBookStringTokens: Set<String> get() = listOfBookStringTokens.toSet()


    val getSetOfBookWords: Set<Word> get() = listOfBookStringTokens.map { createWord(it,book) }.toHashSet()

    /**
     *
     */
    val getListOfBookWords: List<Word> get() = listOfBookStringTokens.map { Word(it, setOf(), setOf(book)) }


    val mapOfBookWordOccurs get() = getListOfBookWords.associate { Pair(it, getListOfBookWords.count {word -> word.word == it.word }) }


    val mapOfBookTokenOccurs get() = listOfBookStringTokens.associate { Pair(it, getListOfBookWords.count {word -> word.word == it }) }

    fun getWordsStatistics(tokensMap: Map<String, Int>): Map<Int, Int> = tokensMap.values.groupBy { it }.mapValues { it.value.size }

    fun sortDescendingTokensMap(tokensMap: Map<String, Int>): Map<String, Int>
            = mapOfBookTokenOccurs.entries.sortedWith(Comparator.comparingInt { -it.value }).associate { Pair(it.key, it.value) }

    fun sortAccendingTokensMap(tokensMap: Map<String, Int>): Map<String, Int>
            = mapOfBookTokenOccurs.entries.sortedWith(Comparator.comparingInt { it.value }).associate { Pair(it.key, it.value) }


}



