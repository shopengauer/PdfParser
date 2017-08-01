package com.pdf.textparser.businesslogic

import org.slf4j.Logger
import org.slf4j.LoggerFactory

data class Book(val title: String, val authors: Set<String> = setOf(), var isbn: String = "")

data class Word(val word: String, val translates: Set<String> = setOf()) {
    fun addTranslate(translate: String) = Word(this.word, translates.plusElement(translate))
}

class BookStatistic(val tokensList: List<String>, val book: Book = Book("default")) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(BookStatistic::class.java)
    }

    /**
     * Set of words found in text
     */
    val wordsSet: Set<String> get() = tokensList.toSet()


    /**
     * Number of word occurs in text
     */
    val tokenOccurs: Map<String, Int> get() = tokensList.groupBy { it }.mapValues { it.value.size }

    /**
     *  total number of tokens in text
     */
    val numberOfTokens: Int get() = tokensList.size

    /**
     *  total number of word in text
     */
    val numberOfWords: Int get() = wordsSet.size


    /**
     * Set of Word object from text
     */
    val wordObjectSet: Set<Word> get() = tokensList.map { Word(it, setOf()) }.toHashSet()

    /**
     * List of Word object from the text
     */
    val wordObjectList: List<Word> get() = tokensList.map { Word(it, setOf()) }

    /**
     * Word objects occurs in text
     */
    val wordObjectOccurs:Map<Word,Int> get() = wordObjectList.groupBy { it }.mapValues { it.value.size }

    /**
     * How many words occurs in text how many time
     */
    fun getWordsStatistics(tokensMap: Map<String, Int>): Map<Int, Int> = tokensMap.values.groupBy { it }.mapValues { it.value.size }

    fun sortDescendingTokensMap(tokensMap: Map<String, Int>): Map<String, Int>
            = tokenOccurs.entries.sortedWith(Comparator.comparingInt { -it.value }).associate { Pair(it.key, it.value) }

    fun sortAscendingTokensMap(tokensMap: Map<String, Int>): Map<String, Int>
            = tokenOccurs.entries.sortedWith(Comparator.comparingInt { it.value }).associate { Pair(it.key, it.value) }


}



