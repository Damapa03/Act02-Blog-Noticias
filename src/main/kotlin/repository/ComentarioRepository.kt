package repository

import model.Comentario

class ComentarioRepository {
    private val db = Dao.getDatabase("blog")
    private val coll = db.getCollection("collComentarios", Comentario::class.java)
    fun createComment(comentario: Comentario){
        coll.insertOne(comentario)
    }

    fun getComments(){

    }
}