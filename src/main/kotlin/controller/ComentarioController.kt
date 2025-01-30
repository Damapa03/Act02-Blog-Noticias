package controller

import model.Comentario
import service.ComentarioService

class ComentarioController {
    private val comentarioService = ComentarioService()

    fun createComentario(comentario: Comentario) {
        comentarioService.createComment(comentario)
    }
}