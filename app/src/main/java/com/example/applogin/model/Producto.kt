package com.example.applogin.model



    data class Producto (
        var id:String,
        var nombre:String,
        var precio:Int,
        var descripcion:String,
        var imageUrl:String,
        var inventario:Int
    ){

        constructor() : this(
            "","",0,"","", 0
        ) {}

    }