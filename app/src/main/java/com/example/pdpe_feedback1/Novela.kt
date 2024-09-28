package com.example.pdpe_feedback1

data class Novela(
    val titulo: String,
    val autor: String,
    val ano: Int,
    val sinopsis: String,
    var esFavorita: Boolean = false
)

