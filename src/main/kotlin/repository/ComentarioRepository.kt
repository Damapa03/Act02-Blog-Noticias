package repository

import com.mongodb.client.model.Filters
import model.Comentario
import java.time.Instant

class ComentarioRepository {
    private val db = Dao.getDatabase("blog")
    private val coll = db.getCollection("collComentarios", Comentario::class.java)
    fun createComment(comentario: Comentario){
        coll.insertOne(comentario)
    }

    fun getComments(fechaPubli: Instant): List<Comentario> {
        val filter = Filters.eq("noticia.fechaPubli", fechaPubli)
        return coll.find(filter).toList()
    }

    fun eliminarComentarios(fechaPubli: Instant) {
        val filter = Filters.eq("noticia.fechaPubli", fechaPubli)
        coll.deleteMany(filter)
    }

}