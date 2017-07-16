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

    val ds = """ Thanks to Kotlin’s support for type inference, most of the extra verbosity associated
with static typing disappears, because you don’t need to declare types explicitly.
If you look at the specifics of Kotlin’s type system, you’ll find many familiar con-
cepts. Classes, interfaces, and generics work in a way very similar to Java, so most of
your Java knowledge should easily transfer to Kotlin. Some things are new, though.
The most important of those is Kotlin’s support for nullable types, which lets you
write more reliable programs by detecting possible null pointer exceptions at com-
pile time. We’ll come back to nullable types later in this chapter and discuss them in
detail in chapter 6.
Another new thing in Kotlin’s type system is its support for function types. To see
what this is about, let’s look at the main ideas of functional programming and see how
it’s supported in Kotlin."""


    @Test
    fun parseTextTest() {
        val matchResults: List<MatchResult> = textOperations.getListOfTokens(testText)

         println(textOperations.filterListOfTokens(matchResults).toSet())

       // println(matchResults.map { it.value })


//
//        val scanner = Scanner(testText)
//        while (scanner.hasNext())
//            println(scanner.nextLine())


//        println(ds.replace("-\n","").replace("\n",""))
//        println(ds)
//        val scanner2 = Scanner(ds)
//        while (scanner2.hasNextLine())
//            println(scanner2.nextLine().contains("\n"))


    }
}