package service

import model.Noticia
import repository.NoticiaRepository

class NoticiaService {
    private val noticiaRepository = NoticiaRepository()

//    fun getNoticias(): List<Noticia> {}

    fun insertNoticia(noticia: Noticia) {
        noticiaRepository.createNoticia(noticia)
    }
}