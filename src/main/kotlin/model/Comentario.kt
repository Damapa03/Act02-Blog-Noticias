package model

import java.time.Instant

data class Comentario(
    val usuario: String,
    val noticia: Noticia,
    val texto: String,
    val fecha: Instant
)
