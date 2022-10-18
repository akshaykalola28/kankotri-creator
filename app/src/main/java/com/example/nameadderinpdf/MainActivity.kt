package com.example.nameadderinpdf

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import com.example.nameadderinpdf.databinding.ActivityMainBinding
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream
import com.tom_roush.pdfbox.pdmodel.font.PDType0Font
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        with(binding) {
            btnCreateCard.setOnClickListener {
                val name = etName.text.toString()
                addName(name, Type.Card)
            }

            btnCreateKankotri.setOnClickListener {
                val name = etName.text.toString()
                addName(name, Type.Kankotri)
            }

            btnShare.setOnClickListener {
                val saveFile = File(filesDir.absolutePath + File.separator + FILE_NAME)
                shareFile(saveFile)
            }
        }
    }

    private fun addName(name: String, type: Type) {
        PDFBoxResourceLoader.init(applicationContext)
        lifecycleScope.launch(Dispatchers.IO) {
            assets.open(type.fileName).use {
                val document = PDDocument.load(it)
                val page = document.getPage(type.pageIndex)
                val contentStream = PDPageContentStream(
                    document,
                    page,
                    PDPageContentStream.AppendMode.APPEND,
                    false,
                    true
                )
                assets.open("HindVadodara.ttf").use { font ->
                    val pdFont = PDType0Font.load(document, font)
                    contentStream.beginText()
                    contentStream.newLineAtOffset(type.x, type.y)
                    contentStream.setNonStrokingColor(type.color.r, type.color.g, type.color.b)
                    contentStream.setFont(pdFont, type.fontSize)
                    contentStream.showText(name)
                    contentStream.endText()
                    contentStream.close()

                    val saveFile = File(filesDir.absolutePath + File.separator + FILE_NAME)
                    saveFile.parentFile?.mkdirs()
                    saveFile.createNewFile()
                    document.save(saveFile)
                    loadPdf(saveFile)
                }
            }
        }
    }

    private fun loadPdf(file: File) {
        binding.pdfView.fromFile(file)
            .load()
    }

    private fun shareFile(file: File) {
        val share = Intent()
        share.action = Intent.ACTION_SEND
        share.type = "application/pdf"
        share.putExtra(
            Intent.EXTRA_STREAM, FileProvider.getUriForFile(
                this,
                BuildConfig.APPLICATION_ID + ".provider",
                file
            )
        )
        startActivityForResult(
            Intent.createChooser(share, "Choose Option"),
            12
        )
    }

    companion object {
        const val FILE_NAME = "Jasmita & Snehal Kalola.pdf"
    }
}