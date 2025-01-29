package controller

import model.Usuario
import service.UsuarioService

class UsuarioController {
    private val usuarioService = UsuarioService()
    fun login(nick: String): Boolean {

        if (nick.trim().isNotEmpty()){
            return usuarioService.searchByNick(nick)
        }else return false
    }

    fun register(usuario: Usuario){
        usuarioService.register(usuario)
    }

}