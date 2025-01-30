package service

import model.Comentario
import repository.ComentarioRepository
import repository.UsuarioRepository
import java.time.Instant

class ComentarioService {
    private val comentarioRepository = ComentarioRepository()
    private val usuarioRepository = UsuarioRepository()

    fun createComment(comentario: Comentario) {

        if (isUserNotBanned(comentario.usuario)){ comentarioRepository.createComment(comentario) }
    }

    fun isUserNotBanned(usuario: String): Boolean {
        val user = usuarioRepository.getUserById(usuario)

        return user.estado
    }

    fun getComentarios(titulo: Instant) {
        comentarioRepository.getComments(titulo)
    }
}