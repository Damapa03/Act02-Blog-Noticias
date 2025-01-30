package repository

import com.mongodb.client.model.Filters
import model.Usuario

class UsuarioRepository {
    private val db = Dao.getDatabase("blog")
    private val coll = db.getCollection("collUsuarios", Usuario::class.java)

    fun register(usuario: Usuario) {

        coll.insertOne(usuario)
    }

    fun login(nick: String): Boolean{

        val user = getUserByNick(nick)

        return nick == user
    }

    fun getUserById(correo: String): String{
        val filtro = Filters.eq("_id", correo)
        val user = coll.find(filtro).first()

        return if (user != null) {
            user._id.toString()
        }else ""
    }

    fun getUserByNick(nick: String): String {
        val filtro = Filters.eq("nick", nick)
        val user = coll.find(filtro).first()

        return if (user != null) {
            user.nick.toString()
        }else ""
    }
}
