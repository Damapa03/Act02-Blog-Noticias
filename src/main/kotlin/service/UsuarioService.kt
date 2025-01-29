package service

import model.Usuario
import repository.UsuarioRepository

class UsuarioService {
    private val usuarioRepository = UsuarioRepository()
    fun searchByNick(nick: String):Boolean{

        return usuarioRepository.login(nick)
    }

    fun register (usuario: Usuario){

        if (!checkIfEmailOrNickExists(usuario._id, usuario.nick)){
            usuarioRepository.register(usuario)
        }else println("El correo o el nick ya existen")
    }

    fun checkIfEmailOrNickExists(email: String, nick: String): Boolean{
            if (email == usuarioRepository.getUserById(email) && nick == usuarioRepository.getUserByNick(nick)){
                return true
            }else return false
    }
}