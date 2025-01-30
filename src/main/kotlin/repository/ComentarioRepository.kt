package repository

import com.mongodb.client.model.Aggregates
import com.mongodb.client.model.Filters
import model.Comentario
import java.time.Instant

class ComentarioRepository {
    private val db = Dao.getDatabase("blog")
    private val coll = db.getCollection("collComentarios", Comentario::class.java)
    fun createComment(comentario: Comentario){
        coll.insertOne(comentario)
    }

    fun getComments(titulo: Instant) {
        val pipeline = listOf(
            Aggregates.lookup(
                "collNoticias",
                "noticia",
                "fechaPubli",
                "noticia_info"
            )
            , Aggregates.match(
                Filters.eq("fechaPubli", titulo)
            )
        )
        coll.aggregate(pipeline).toList()
    }
}