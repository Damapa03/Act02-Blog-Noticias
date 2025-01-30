import controller.ComentarioController
import controller.NoticiaControler
import controller.UsuarioController
import model.Comentario
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
                num = readln().toInt()
            } catch (e: Exception) {
                println("Introduzaca un numero")
            }
            if (num == 1) {
                println("Introduzca el nick")
                val nick = readln()

                if (usuarioController.login(nick)) {
                    println("Login exitoso")
                    loged = !loged
                    userLoged = nick
                } else println("El nick no existe")
            }
            if (num == 2) {
                print("Ingrese el correo: ")
                val id = readlnOrNull().orEmpty()

                print("Ingrese nombre: ")
                val nombre = readlnOrNull().orEmpty()

                print("Ingrese nick: ")
                val nick = readlnOrNull().orEmpty()

                print("Ingrese calle: ")
                val calle = readlnOrNull().orEmpty()

                print("Ingrese número: ")
                val num = readlnOrNull().orEmpty()

                print("Ingrese puerta: ")
                val puerta = readlnOrNull().orEmpty()

                print("Ingrese código postal: ")
                val cp = readlnOrNull().orEmpty()

                print("Ingrese ciudad: ")
                val ciudad = readlnOrNull().orEmpty()

                val direccion = Direccion(calle, num, puerta, cp, ciudad)

                print("Ingrese cantidad de teléfonos: ")
                val cantidadTelefonos = readlnOrNull()?.toIntOrNull() ?: 0

                val telefonos = mutableListOf<String>()

                for (i in 1..cantidadTelefonos) {
                    print("Ingrese teléfono $i: ")
                    telefonos.add(readlnOrNull().orEmpty())
                }

                val usuario = Usuario(id, nombre, nick, true, direccion, telefonos)
                if (usuarioController.register(usuario)) {
                    loged = !loged
                    userLoged = usuario.nick
                } else println("El correo o el nick ya existen")
            }
            if (num == 3) {
                break
            }
            if (num < 1 || num > 3) {
                println("Ingrese una opcion valido")
            }
        }
        if (loged) {
            println("1. Publicar noticia\n2. Ver noticias\n3. Modificar noticia\n4. Eliminar noticia\n5. Salir")
            var numMenu = 0
            try {
                numMenu = readln().toInt()
            } catch (e: Exception) {
                println("Introduzaca un numero")
            }
            if (numMenu == 1) {
                println("Ingrese el título de la noticia:")
                val titulo = readlnOrNull()?.takeIf { it.isNotBlank() } ?: "Sin título"

                println("Ingrese el cuerpo de la noticia:")
                val cuerpo = readlnOrNull()?.takeIf { it.isNotBlank() } ?: "Sin contenido"

                println("Ingrese los tags (separados por comas, opcional):")
                val tagsInput = readlnOrNull()
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
            if (numMenu == 2) {
                while (true) {
                    println("1. Ver noticias de un usuario\n2. Buscar notica por etiquetas\n3. Ver 10 ultimas noticias\n4. Ver comentarios de una noticia\n5. Salir")
                    var numMostrar = 0
                    try {
                        numMostrar = readln().toInt()
                    } catch (e: Exception) {
                        println("Introduzaca un numero")
                    }

                    if (numMostrar == 1) {
                        println("Introduzca el nombre del usuario")
                        val user = readln()
                        val noticias = noticiaControler.getNoticiasByNick(user)

                        noticias.forEachIndexed { index, noticia ->
                            println("${index + 1} ${noticia.titulo}\n${noticia.cuerpo}\n${noticia.fechaPubli}\nAutor: ${noticia.autor}\nTags: ${noticia.tags}")
                        }
                        while (true) {
                            println("¿Quieres añadir algun comentario?\n 1. Si\n 2. No")
                            var numYN = 0
                            try {
                                numYN = readln().toInt()
                            } catch (e: Exception) {
                                println("Introduzaca un numero")
                            }
                            if (numYN == 1) {
                                println("¿A que noticia quiere añadir un comentario?")
                                var numComent = 0
                                try {
                                    numComent = readln().toInt() - 1
                                } catch (e: Exception) {
                                    println("Introduzca un numero")
                                }
                                println("Escriba su comentario")
                                val comentarioText = readln()

                                val comentario = Comentario(userLoged, noticias[numComent], comentarioText, Instant.now())

                                comentarioController.createComentario(comentario)

                            }
                            if (numYN == 2) {
                                break
                            }
                            if (numYN < 1 || numYN > 2) {
                                println("Ingrese una opcion valido")
                            }

                        }

                    }
                    if (numMostrar == 2) {
                        println("¿Cual es la etiqueta por la que quiere buscar?")
                        val tag = readln()
                        noticiaControler.getNoticiasByTag(tag).forEach {
                            println(it)
                        }
                    }
                    if (numMostrar == 3) {
                        noticiaControler.get10LastNoticias().forEachIndexed { index, noticia ->
                            println("${index + 1} ${noticia.titulo}\n${noticia.cuerpo}\n${noticia.fechaPubli}\nAutor: ${noticia.autor}\nTags: ${noticia.tags}")
                        }
                    }
                    if (numMostrar == 4) {
                        println("Tus noticias")
                        val noticias = noticiaControler.getNoticiasByNick(userLoged)
                        var numNoticia = 0
                        noticias.forEachIndexed { index, noticia ->
                            println("${index + 1}. ${noticia.titulo}\n${noticia.cuerpo}\n${noticia.fechaPubli}\n${noticia.tags?.joinToString() ?: "Sin tags"}")
                        }
                        println("¿De cual noticia quieres ver los comentarios?")
                        try {
                            numNoticia = readln().toInt() - 1
                        } catch (e: Exception) {
                            println("Introduzca un numero")
                        }
                        comentarioController.getComentarios(noticias[numNoticia].fechaPubli).forEach {
                            println("${it.usuario}:\n ${it.texto}")
                        }
                    }
                    if (numMostrar == 5) {
                        break
                    }
                    if (numMostrar < 1 || numMostrar > 5) {
                        println("Ingrese una opcion valido")
                    }
                }
            }
            if (numMenu == 3) {
                println("Tus noticias")
                val noticias = noticiaControler.getNoticiasByNick(userLoged)
                var numNoticia = 0
                noticias.forEachIndexed { index, noticia ->
                    println("${index + 1}. ${noticia.titulo}\n${noticia.cuerpo}\n${noticia.fechaPubli}\n${noticia.tags?.joinToString() ?: "Sin tags"}")
                }
                println("¿Que noticia quiere modificar?")
                try {
                    numNoticia = readln().toInt() - 1
                } catch (e: Exception) {
                    println("Introduzca un numero")
                }

                val id = noticias[numNoticia].fechaPubli

                println("Ingrese el nuevo título de la noticia:")
                val titulo = readlnOrNull()?.takeIf { it.isNotBlank() } ?: ""

                println("Ingrese el nuevo cuerpo de la noticia:")
                val cuerpo = readlnOrNull()?.takeIf { it.isNotBlank() } ?: ""

                println("Ingrese los nuevos tags (separados por comas, opcional):")
                val tagsInput = readlnOrNull()
                val tags = tagsInput?.split(",")?.map { it.trim() }?.filter { it.isNotEmpty() }

                noticiaControler.updateNoticia(id, titulo, cuerpo, tags)
            }
            if (numMenu == 4){
                println("Tus noticias")
                val noticias = noticiaControler.getNoticiasByNick(userLoged)
                var numEleminiar = 0
                noticias.forEachIndexed { index, noticia ->
                    println("${index + 1}. ${noticia.titulo}\n${noticia.cuerpo}\n${noticia.fechaPubli}\n${noticia.tags?.joinToString() ?: "Sin tags"}")
                }
                println("¿Que noticia quiere eliminar?")
                try {
                    numEleminiar = readln().toInt() - 1
                } catch (e: Exception) {
                    println("Introduzca un numero")
                }

                noticiaControler.elminarNoticia(noticias[numEleminiar].fechaPubli)
            }
            if (numMenu == 5) {
                loged = !loged
            }
            if (numMenu < 1 || numMenu > 5) {
                println("Ingrese una opcion valido")
            }
        }
    }
}
