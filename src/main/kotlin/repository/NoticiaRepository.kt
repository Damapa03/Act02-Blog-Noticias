package repository

import model.Noticia

class NoticiaRepository {
    private val db = Dao.getDatabase("blog")
    private val coll = db.getCollection("collNoticias", Noticia::class.java)
    fun createNoticia(noticia: Noticia){
        coll.insertOne(noticia)
    }

    fun updateNoticia(noticia: Noticia){

    }

    fun getNoticiaByUser(){

    }

    fun getNoticiaByTag(){

    }

    fun getLast10Noticias(){

    }
}