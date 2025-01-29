package repository

import com.mongodb.client.FindIterable
import com.mongodb.client.model.Filters
import model.Noticia
import org.bson.Document
import java.time.Instant

class NoticiaRepository {
    private val db = Dao.getDatabase("blog")
    private val coll = db.getCollection("collNoticias", Noticia::class.java)
    fun createNoticia(noticia: Noticia){
        coll.insertOne(noticia)
    }

    fun updateNoticia(id: Instant, titulo: String, cuerpo: String, tags: List<String>?) {

        var nuevoTitulo = titulo
        var nuevoCuerpo = cuerpo
        var nuevoTags = tags

        val filtro = Filters.eq("fechaPubli", id)

        val noticia = coll.find(filtro).toList()

        if(nuevoTitulo == ""){
            nuevoTitulo = noticia[0].titulo
        }
        if (nuevoCuerpo == ""){
            nuevoCuerpo = noticia[0].cuerpo
        }
        if (nuevoTags.isNullOrEmpty()){
            nuevoTags = noticia[0].tags
        }

        val update = Document("\$set", Document("titulo", nuevoTitulo))
            .append("\$set", Document("cuerpo", nuevoCuerpo))
            .append("\$set", Document("tags", nuevoTags))

        coll.updateOne(filtro, update)
    }

    fun getNoticiaByUser(nick: String): List<Noticia> {
        val filter = Filters.eq("autor", nick)

        return coll.find(filter).toList()
    }

    fun getNoticiaByTag(tag: String): FindIterable<Noticia> {
        val filter = Filters.eq("tags", tag)
        return coll.find(filter)
    }

    fun getLast10Noticias(): List<Noticia> {
        return coll.find().toList().sortedByDescending { it.fechaPubli }.subList(0, 10)
    }
}