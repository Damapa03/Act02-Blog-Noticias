package repository

class ComentarioRepository {
    private val db = Dao.getDatabase("blog")
    private val coll = db.getCollection("collComentarios")
    fun createComment(){

    }

    fun getComments(){

    }
}