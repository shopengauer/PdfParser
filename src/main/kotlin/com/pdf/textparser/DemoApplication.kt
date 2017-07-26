package com.pdf.textparser

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Repository
import java.io.File

@SpringBootApplication
@ComponentScan
class DemoApplication

fun main(args: Array<String>) {
    SpringApplication.run(DemoApplication::class.java, *args)
}

@Repository
class PdfReader : PdfFileOperation {

    override fun createBlankPdf() = PDDocument()

    override fun createBlankPdfFile(fileName: String) {
        createBlankPdf()
    }

    fun readPdfFile(fileName: String): PDDocument? {
        val pdfDocument = PDDocument.load(File(fileName))
        return PDDocument()
    }


    val prefix = "~/"

    fun createBlankPdf(fileName: String) {
        val doc = PDDocument()
        doc.use {
            val pdPage = PDPage()
            doc.addPage(pdPage)
            print("${System.getProperty("user.home")}${File.separator}$fileName")
          //  doc.save(getHomePath(fileName))
        }
    }


    fun getHomePath(fileName: String) = "${System.getProperty("user.home")}${File.separator}$fileName"

}

interface PdfFileOperation {

    fun createBlankPdf(): PDDocument

    fun createBlankPdfFile(fileName: String)

}

