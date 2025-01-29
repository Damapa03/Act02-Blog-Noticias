package controller

import com.mongodb.client.FindIterable
import model.Noticia
import service.NoticiaService
import java.time.Instant

class NoticiaControler {
   private val noticiaService: NoticiaService = NoticiaService()
    fun createNoticia(noticia:Noticia){
        noticiaService.insertNoticia(noticia)
    }

    fun updateNoticia(id: Instant, titulo: String, cuerpo: String, tags: List<String>?) {
        noticiaService.updateNoticia(id, titulo,cuerpo,tags)
    }

    fun getNoticiasByNick(userLoged: String): List<Noticia> {
        return noticiaService.getNoticiasByNick(userLoged)
    }

    fun getNoticiasByTag(tag: String): FindIterable<Noticia> {
        return noticiaService.getNoticiasByTag(tag)
    }

    fun get10LastNoticias(): List<Noticia> {
        return noticiaService.get10LastNoticias()
    }
}