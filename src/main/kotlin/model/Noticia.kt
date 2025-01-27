package model

import java.util.*

data class Noticia(
    val titulo: String,
    val cuerpo: String,
    val fechaPubli: Date,
    val autor: String, // Foreign key
    val tags: List<String>?
)
