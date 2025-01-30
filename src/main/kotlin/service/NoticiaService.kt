package service

import com.mongodb.client.FindIterable
import model.Noticia
import repository.NoticiaRepository
import java.time.Instant

class NoticiaService {
    private val noticiaRepository = NoticiaRepository()
    private val comentarioService = ComentarioService()

    fun insertNoticia(noticia: Noticia) {
        noticiaRepository.createNoticia(noticia)
    }

    fun updateNoticia(id: Instant, titulo: String, cuerpo: String, tags: List<String>?) {
        noticiaRepository.updateNoticia(id, titulo, cuerpo,tags)
    }

    fun getNoticiasByNick(userLoged: String): List<Noticia> {
        return noticiaRepository.getNoticiaByUser(userLoged)
    }

    fun getNoticiasByTag(tag: String): FindIterable<Noticia> {
        return noticiaRepository.getNoticiaByTag(tag)
    }

    fun get10LastNoticias(): List<Noticia> {
        return noticiaRepository.getLast10Noticias()
    }

    fun eliminiarNoticia(fechaPubli: Instant) {
        comentarioService.eliminarComentarios(fechaPubli)
        noticiaRepository.eliminarNoticia(fechaPubli)
    }
}