package model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty

data class Usuario(
    @BsonId
    val _id: String,
    val nombre: String,
    val nick: String,
    val estado: Boolean = true,
    val direccion: Direccion,
    @BsonProperty("telefonos")
    val tlfn: List<String>,
)
