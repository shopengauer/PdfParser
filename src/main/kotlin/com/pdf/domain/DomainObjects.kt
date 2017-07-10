package com.pdf.domain


class Book(title: String, authors: Set<String> = setOf(), isbn: String = "") {
    private val statisticsOfWords: Map<Word, Int> = hashMapOf()
}


data class Word(val word: String, private val translates: Set<String> = setOf(), val books: Set<Book> = setOf()) {
    fun addTranslateWord(translate: String) = Word(this.word, translates.plusElement(translate), this.books)
    fun addBook(book: Book) = Word(this.word, this.translates, books.plusElement(book))
}




