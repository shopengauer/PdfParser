package com.pdf.textparser

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class TextParserTest {

    @Autowired lateinit var textOperations: TextOperations

    // todo подумать как быть со словами при переносе их на новую строку

    val testText = "But Kotlin works in other contexts as well. For example, you can use the Intel Multi-OS\n" +
            "Engine (https://software.intel.com/en-us/multi-os-engine) to run Kotlin code on iOS\n" +
            "devices. To build desktop applications, you can use Kotlin together with TornadoFX\n" +
            "(https://github.com/edvin/tornadofx) and JavaFX.1\n" +
            "In addition to Java, Kotlin can be compiled to JavaScript, allowing you to run Kot-\n" +
            "lin code in the browser. But as of this writing, JavaScript support is still being explored\n" +
            "and prototyped at JetBrains, so it’s out of scope for this book. Other platforms are also\n" +
            "under consideration for future versions of the language.\n" +
            "As you can see, Kotlin’s target is quite broad. Kotlin doesn’t focus on a single prob-\n" +
            "lem domain or address a single type of challenge faced by software developers today.\n" +
            "Instead, it provides across-the-board productivity improvements for all tasks that come\n" +
            "up during the development process. It gives you an excellent level of integration with\n" +
            "libraries that support specific domains or programming paradigms. Let’s look next at\n" +
            "the key qualities of Kotlin as a programming language."

    @Test
    fun parseTextTest() {
      val matchResults : List<MatchResult> = textOperations.getListOfTokens(testText)
      println(matchResults.map { it.value })

      val scanner = Scanner(testText)
       while(scanner.hasNext())
          println(scanner.nextLine())


    }
}