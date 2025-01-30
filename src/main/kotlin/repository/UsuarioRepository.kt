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

        val user = checkUserByNick(nick)

        return nick == user
    }

    fun getUserByNick(userNick: String): Usuario? {
        val filtro = Filters.eq("nick", userNick)
        val user = coll.find(filtro).first()

        return user
    }
    fun checkUserById(correo: String): String{
        val filtro = Filters.eq("_id", correo)
        val user = coll.find(filtro).first()

        return user?._id ?: ""
    }

    fun checkUserByNick(nick: String): String {
        val filtro = Filters.eq("nick", nick)
        val user = coll.find(filtro).first()

        return if (user != null) {
            user.nick.toString()
        }else ""
    }
}
