package com.example.nameadderinpdf

sealed class Type(
    val fileName: String,
    val x: Float,
    val y: Float,
    val pageIndex: Int,
    val fontSize: Float,
    val color: RGB
) {
    object Card : Type("card.pdf", 230f, 1050f, 0, 38f, RGB(204, 14, 0))
    object Kankotri : Type("kankotri.pdf", 160f, 368f, 2, 14f, RGB(6, 117, 6))
}

data class RGB(
    val r: Int,
    val g: Int,
    val b: Int
)