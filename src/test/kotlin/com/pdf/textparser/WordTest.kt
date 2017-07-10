package com.pdf.textparser

import com.pdf.domain.Word
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class WordTest {

    @Test
    fun createEnWordTest() {
      val word: Word = Word("Hello")
      println(word.addTranslateWord("Привет").addTranslateWord("Здорово"))

    }
}