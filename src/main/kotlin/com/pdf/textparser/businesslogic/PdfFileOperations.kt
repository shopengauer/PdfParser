package com.pdf.textparser.businesslogic

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import java.io.File

fun getHomePath(fileName: String) = "${System.getProperty("user.home")}${File.separator}$fileName"

fun readPdfFile(fileName: String): PDDocument? = PDDocument.load(File(fileName))

fun readPdfDocumentText(pdfDocument: PDDocument?): String {
    pdfDocument.use {
        return PDFTextStripper().getText(pdfDocument)
    }

}

