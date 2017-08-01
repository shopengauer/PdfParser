package com.pdf.textparser.businesslogic

import org.springframework.stereotype.Service


interface TextOperations {

    fun getListOfTokens(text: String, regexp: Regex = "[A-Za-z]+".toRegex()): List<MatchResult>

    fun filterListOfTokens(tokens: List<MatchResult>): List<String>

}

@Service
class SimpleTextOperations : TextOperations {

    override fun getListOfTokens(text: String, regexp: Regex): List<MatchResult>
            = regexp.findAll(text.replace("-\n","").replace("\n"," ")).toList()


    override fun filterListOfTokens(tokens: List<MatchResult>): List<String> = tokens
            .map { it.value }
            .map {
                when {
                    it.length > 1 && it[0].isUpperCase() && it[1].isUpperCase() -> it
                    else -> it.toLowerCase()
                }
            }.filter { it.length > 1 }



}