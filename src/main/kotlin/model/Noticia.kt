package model

import java.time.Instant

data class Noticia(
    val titulo: String,
    val cuerpo: String,
    val fechaPubli: Instant,
    val autor: String, // Foreign key
    val tags: List<String>?
)
