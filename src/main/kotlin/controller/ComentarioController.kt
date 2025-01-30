package controller

import model.Comentario
import service.ComentarioService
import java.time.Instant

class ComentarioController {
    private val comentarioService = ComentarioService()

    fun createComentario(comentario: Comentario) {
        comentarioService.createComment(comentario)
    }

    fun getComentarios(titulo: Instant): List<Comentario> {
       return comentarioService.getComentarios(titulo)
    }
}