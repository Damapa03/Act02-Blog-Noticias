import controller.ComentarioController
import controller.NoticiaControler
import controller.UsuarioController
import model.Direccion
import model.Noticia
import model.Usuario
import java.time.Instant

fun main() {
    val usuarioController = UsuarioController()
    val noticiaControler = NoticiaControler()
    val comentarioController = ComentarioController()
    var userLoged = ""
    var loged = false

    var num = 0

    while (true) {
        if (!loged) {
            println("1. Login\n2. Register\n3. Salir")
            try {
                num = readLine()!!.toInt()
            } catch (e: Exception) {
                println("Introduzaca un numero")
            }
            if (num == 1) {
                println("Introduzca el nick")
                val nick = readLine()!!

                if (usuarioController.login(nick)) {
                    println("Login exitoso")
                    loged = !loged
                    userLoged = nick
                }else println("El nick no existe")
            }
            if (num == 2) {
                print("Ingrese el correo: ")
                val id = readLine().orEmpty()

                print("Ingrese nombre: ")
                val nombre = readLine().orEmpty()

                print("Ingrese nick: ")
                val nick = readLine().orEmpty()

                print("Ingrese calle: ")
                val calle = readLine().orEmpty()

                print("Ingrese número: ")
                val num = readLine().orEmpty()

                print("Ingrese puerta: ")
                val puerta = readLine().orEmpty()

                print("Ingrese código postal: ")
                val cp = readLine().orEmpty()

                print("Ingrese ciudad: ")
                val ciudad = readLine().orEmpty()

                val direccion = Direccion(calle, num, puerta, cp, ciudad)

                print("Ingrese cantidad de teléfonos: ")
                val cantidadTelefonos = readLine()?.toIntOrNull() ?: 0

                val telefonos = mutableListOf<String>()
                for (i in 1..cantidadTelefonos) {
                    print("Ingrese teléfono $i: ")
                    telefonos.add(readLine().orEmpty())
                }

                val usuario = Usuario(id, nombre, nick, true, direccion, telefonos)
                usuarioController.register(usuario)
                loged = !loged
                userLoged = usuario.nick
            }
            if (num == 3) {
                break
            }
            if (num < 1 || num > 3) {
                println("Ingrese una opcion valido")
            }
        }
        if (loged) {
            println("1. Publicar noticia\n2. Ver noticias\n3. Modificar Noticia\n4. Salir")
            try {
                num = readLine()!!.toInt()
            } catch (e: Exception) {
                println("Introduzaca un numero")
            }
            if (num == 1) {
                println("Ingrese el título de la noticia:")
                val titulo = readLine()?.takeIf { it.isNotBlank() } ?: "Sin título"

                println("Ingrese el cuerpo de la noticia:")
                val cuerpo = readLine()?.takeIf { it.isNotBlank() } ?: "Sin contenido"

                println("Ingrese los tags (separados por comas, opcional):")
                val tagsInput = readLine()
                val tags = tagsInput?.split(",")?.map { it.trim() }?.filter { it.isNotEmpty() }

                val noticia = Noticia(
                    titulo = titulo,
                    cuerpo = cuerpo,
                    fechaPubli = Instant.now(),
                    autor = userLoged,
                    tags = tags
                )

                noticiaControler.createNoticia(noticia)
            }
            if (num == 2) {
                while (true) {
                    println("1. Ver noticias de un usuario\n2. Buscar notica por etiquetas\n3. Ver 10 ultimas noticias\n4. Ver comentarios de una noticia\n5. Salir")
                    try {
                        num = readLine()!!.toInt()
                    } catch (e: Exception) {
                        println("Introduzaca un numero")
                    }

                    if (num == 1) {

                    }
                    if (num == 2) {

                    }
                    if (num == 3) {

                    }
                    if (num == 4) {

                    }
                    if (num == 5) {
                        break
                    }
                    if (num < 1 || num > 5) {
                        println("Ingrese una opcion valido")
                    }
                }
            }
            if (num == 3) {

            }
            if (num == 4) {
                loged = !loged
            }
            if (num < 1 || num > 4) {
                println("Ingrese una opcion valido")
            }
        }
    }
}