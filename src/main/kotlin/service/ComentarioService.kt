package service

import model.Comentario
import repository.ComentarioRepository
import repository.UsuarioRepository
import java.time.Instant

class ComentarioService {
    private val comentarioRepository = ComentarioRepository()
    private val usuarioRepository = UsuarioRepository()

    fun createComment(comentario: Comentario) {

        if (isUserNotBanned(comentario.usuario)){ comentarioRepository.createComment(comentario)
        }else println("Usuario baneado")
    }

    fun isUserNotBanned(usuario: String): Boolean {
        val user = usuarioRepository.getUserByNick(usuario)

        return user?.estado ?: false
    }

    fun getComentarios(titulo: Instant): List<Comentario> {
        return comentarioRepository.getComments(titulo)
    }

    fun eliminarComentarios(fechaPubli: Instant) {
        if(comentarioRepository.getComments(fechaPubli).isNotEmpty()){
            comentarioRepository.eliminarComentarios(fechaPubli)
        }
    }
}