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
    object Kankotri : Type("vastu.pdf", 140f, 604f, 0, 16f, RGB(222, 203, 100))
}

data class RGB(
    val r: Int,
    val g: Int,
    val b: Int
)