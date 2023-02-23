package com.example.nameadderinpdf

sealed class Type(
    val fileName: String,
    val x: Float,
    val y: Float,
    val pageIndex: Int,
    val fontSize: Float,
    val color: RGB
) {
//    object Card : Type("card.pdf", 230f, 1050f, 0, 38f, RGB(204, 14, 0))
    object Kankotri : Type("kankotri.pdf", 587f, 548f, 0, 14f, RGB(230, 11, 32))
}

data class RGB(
    val r: Int,
    val g: Int,
    val b: Int
)