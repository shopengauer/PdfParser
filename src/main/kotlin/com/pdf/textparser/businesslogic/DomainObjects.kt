package com.pdf.textparser.businesslogic

import org.slf4j.Logger
import org.slf4j.LoggerFactory

data class Book(val title: String, val authors: Set<String> = setOf(), var isbn: String = "")

data class Word(val word: String, val translates: Set<String> = setOf()) {
    fun addTranslate(translate: String) = Word(this.word, translates.plusElement(translate))
}


class BookStatistic(val text: String, val book: Book = Book("default")) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(BookStatistic::class.java)
    }

    /**
     * Разбирает исходный текст на токены
     */
    val tokenList: List<String> = "[A-Za-z]+".toRegex().findAll(text.replace("-\n", "").replace("\n", " ")).toList()
            .map { it.value }
            .map {
                when {
                    it.length > 1 && it[0].isUpperCase() && it[1].isUpperCase() -> it
                    else -> it.toLowerCase()
                }
            }.filter { it.length > 1 }


    /**
     * Возвращает множество слов присутствующих в тексте
     */
    val wordsSet: Set<String> get() = tokenList.toSet()


    /**
     *  Возвращает количество появления каждого слова в исходном тексте
     */
    val tokenOccurs: Map<String, Int> get() = tokenList.groupBy { it }.mapValues { it.value.size }

    /**
     *  Общее количество токенов в тексте
     */
    val numberOfTokens: Int get() = tokenList.size

    /**
     *  Общее количество слов в тексте
     */
    val numberOfWords: Int get() = wordsSet.size


    /**
     *  Создает множество объектов типа Word найденных в тексте
     */
    val wordObjectSet: Set<Word> get() = tokenList.map { Word(it, setOf()) }.toHashSet()

    /**
     * Возвращает список токенов обернутых в объект Word
     */
    val wordObjectList: List<Word> get() = tokenList.map { Word(it, setOf()) }

    /**
     * Мапа отображает количество появления каждого слова в исходном тексте
     */
    val wordObjectOccurs: Map<Word, Int> get() = wordObjectList.groupBy { it }.mapValues { it.value.size }

    /**
     * Отображает сколько слов встречается в тексте столео то раз
     */
    val wordsStatistics: Map<Int, Int> get() = tokenOccurs.values.groupBy { it }.mapValues { it.value.size }

    fun sortDescendingTokensMap(): Map<String, Int>
            = tokenOccurs.entries.sortedWith(Comparator.comparingInt { -it.value }).associate { Pair(it.key, it.value) }

    fun sortAscendingTokensMap(): Map<String, Int>
            = tokenOccurs.entries.sortedWith(Comparator.comparingInt { it.value }).associate { Pair(it.key, it.value) }


}



