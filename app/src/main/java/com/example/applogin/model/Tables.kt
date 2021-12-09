package com.example.applogin.model

class Tables {

    companion object{
        var information:Map<String, String> = mapOf(
            "TABLE_NAME" to "usuarios",
            "_id" to "id",
            "_nombre" to "nombre",
            "_direccion" to "direccion",
            "_telefono" to "telefono",
            "_correo" to "correo"
        )
    }
}