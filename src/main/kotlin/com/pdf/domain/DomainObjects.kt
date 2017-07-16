package com.pdf.domain

import org.slf4j.Logger
import org.slf4j.LoggerFactory


data class Book(val title: String, val authors: Set<String> = setOf(), var isbn: String = "")

data class Word(val word: String, val translates: Set<String> = setOf(), val books: Set<Book> = setOf()) {
    fun addTranslate(translate: String) = Word(this.word, translates.plusElement(translate), this.books)
    fun addBook(book: Book) = Word(this.word, this.translates, books.plusElement(book))
}

class BookWordStatistics(val listOfBookStringTokens: List<String>, val book: Book) {

    val logger: Logger = LoggerFactory.getLogger("com.pdf.domain.BookWordStatistics")

    val getSetOfBookStringTokens: Set<String> get() {
        return listOfBookStringTokens.toSet()
    }

    val getSetOfBookWords: Set<Word> get() {
        return listOfBookStringTokens.map { Word(it, setOf(), setOf(book)) }.toHashSet()
    }

    val getListOfBookWords: List<Word> get() {
        return listOfBookStringTokens.map { Word(it, setOf(), setOf(book)) }
    }

    val mapOfBookWordOccurs: Map<Word, Int> get() {
        val mapOfBookWordOccurs = hashMapOf<Word, Int>()
        getListOfBookWords.forEach {
            if (mapOfBookWordOccurs.containsKey(it))
                mapOfBookWordOccurs.put(it, mapOfBookWordOccurs[it]!! + 1)
            else
                mapOfBookWordOccurs.put(it, 1)
        }
        return mapOfBookWordOccurs
    }

    val mapOfBookTokenOccurs: Map<String, Int> get() {
        val mapOfBookTokenOccurs = hashMapOf<String, Int>()
        listOfBookStringTokens.forEach {
            if (mapOfBookTokenOccurs.containsKey(it))
                mapOfBookTokenOccurs.put(it, mapOfBookTokenOccurs[it]!! + 1)
            else
                mapOfBookTokenOccurs.put(it, 1)
        }
        return mapOfBookTokenOccurs
    }

    fun getWordsStatistics(tokensMap: Map<String,Int>) : Map<Int,Int>{
        val tokenOccursSet = tokensMap.values.toSet()
        val tokenOccursMap = hashMapOf<Int,Int>()

        tokensMap.values.forEach {
            if(!tokenOccursMap.containsKey(it))
                tokenOccursMap.put(it,1)
            else
                tokenOccursMap.put(it, tokenOccursMap[it]!! + 1)
             }

        return tokenOccursMap
    }

    fun sortDescendingTokensMap(tokensMap:Map<String,Int>) : Map<String,Int>
            = mapOfBookTokenOccurs.entries.sortedWith(Comparator.comparingInt { -it.value }).associate { Pair(it.key,it.value) }

    fun sortAccendingTokensMap(tokensMap:Map<String,Int>) : Map<String,Int>
            = mapOfBookTokenOccurs.entries.sortedWith(Comparator.comparingInt { it.value }).associate { Pair(it.key,it.value) }


}



