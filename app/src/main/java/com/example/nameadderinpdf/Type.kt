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
    object Kankotri : Type("invitation.pdf", 110f, 455f, 0, 17f, RGB(255,255,255))
}

data class RGB(
    val r: Int,
    val g: Int,
    val b: Int
)