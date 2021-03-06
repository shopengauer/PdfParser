package com.pdf.textparser

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import java.io.File

fun getHomePath(fileName: String) = "${System.getProperty("user.home")}${File.separator}$fileName"

fun readPdfFile(fileName: String): PDDocument? = PDDocument.load(File(fileName))

fun readPdfDocumentText(pdfDocument: PDDocument?): String = PDFTextStripper().getText(pdfDocument)

