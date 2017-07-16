package com.pdf.textparser

import org.springframework.stereotype.Service


interface TextOperations {

    fun getListOfTokens(text: String, regexp: Regex = "[A-Za-z]+".toRegex()): List<MatchResult>

    fun filterListOfTokens(tokens: List<MatchResult>): List<String>

    fun getWordStatisticsMap(tokens: List<String>): Map<String, Int>

}

@Service
class SimpleTextOperations : TextOperations {

    override fun getListOfTokens(text: String, regexp: Regex): List<MatchResult>
            = regexp.findAll(text.replace("-\n","").replace("\n","")).toList()


    override fun filterListOfTokens(tokens: List<MatchResult>): List<String> = tokens
            .map { it.value }
            .map {
                when {
                    it[0].isUpperCase() && it[1].isUpperCase() -> it
                    else -> it.toLowerCase()
                }
            }.filter { it.length > 1 }




    override fun getWordStatisticsMap(tokens: List<String>): Map<String, Int> {
        val wordCountMap = hashMapOf<String, Int>()

        tokens.forEach {
            if (!wordCountMap.containsKey(it))
                wordCountMap.put(it, 1)
            else
                wordCountMap.put(it, (wordCountMap[it]!! + 1))
        }

        return wordCountMap
    }
}