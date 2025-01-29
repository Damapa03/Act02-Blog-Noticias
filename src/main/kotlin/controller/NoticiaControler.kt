package controller

import model.Noticia
import service.NoticiaService

class NoticiaControler {
   private val noticiaService: NoticiaService = NoticiaService()
    fun createNoticia(noticia:Noticia){
        noticiaService.insertNoticia(noticia)
    }
}