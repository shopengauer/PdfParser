package com.pdf.textparser

import io.vertx.core.json.JsonObject
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class JsonObjectTest {


    @Test
    fun name() {
      val json:JsonObject = JsonObject()
      val jsonInternal:JsonObject = JsonObject()
      jsonInternal.put("internal","Key")
        json.put("name","jsonName")
        json.put("name",jsonInternal)
        json.put("num",50)
        println(json)

    }
}