package service

import model.Usuario
import repository.UsuarioRepository

class UsuarioService {
    private val usuarioRepository = UsuarioRepository()
    fun searchByNick(nick: String):Boolean{

        return usuarioRepository.login(nick)
    }

    fun register (usuario: Usuario): Boolean {

        if (!checkIfEmailOrNickExists(usuario._id, usuario.nick)){
            usuarioRepository.register(usuario)
            return true
        }else return false
    }

    fun checkIfEmailOrNickExists(email: String, nick: String): Boolean{
        return if (email == usuarioRepository.checkUserById(email) || nick == usuarioRepository.checkUserByNick(nick)){
            true
        }else false
    }
}