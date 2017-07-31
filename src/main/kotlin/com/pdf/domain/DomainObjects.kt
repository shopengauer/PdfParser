package com.pdf.domain

import org.slf4j.Logger
import org.slf4j.LoggerFactory

data class Book(val title: String, val authors: Set<String> = setOf(), var isbn: String = "")

data class Word(val word: String, val translates: Set<String> = setOf()) {
    fun addTranslate(translate: String) = Word(this.word, translates.plusElement(translate))
}

class BookStatistic(val tokensList: List<String>, val book: Book) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(BookStatistic::class.java)
    }

    val tokensSet: Set<String> get() = tokensList.toSet()

    val tokenOccurs get() = tokensList.groupBy { it }.mapValues { it.value.size }

    val numberOfTokens: Int get() = tokensList.size

    val numberOfWords: Int get() = tokensSet.size

    val wordObjectSet: Set<Word> get() = tokensList.map { Word(it, setOf()) }.toHashSet()

    val wordObjectList: List<Word> get() = tokensList.map { Word(it, setOf()) }

    val wordObjectOccurs get() = wordObjectList.groupBy { it }.mapValues { it.value.size }


    fun getWordsStatistics(tokensMap: Map<String, Int>): Map<Int, Int> = tokensMap.values.groupBy { it }.mapValues { it.value.size }

    fun sortDescendingTokensMap(tokensMap: Map<String, Int>): Map<String, Int>
            = tokenOccurs.entries.sortedWith(Comparator.comparingInt { -it.value }).associate { Pair(it.key, it.value) }

    fun sortAccendingTokensMap(tokensMap: Map<String, Int>): Map<String, Int>
            = tokenOccurs.entries.sortedWith(Comparator.comparingInt { it.value }).associate { Pair(it.key, it.value) }


}



