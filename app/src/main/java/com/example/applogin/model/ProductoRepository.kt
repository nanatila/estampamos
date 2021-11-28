package com.example.applogin.model

class ProductoRepository {

    private var productos = listOf(

        Producto(
            1,
            "Camiseta Code ",
            35000,
            "Camiseta Negra Estampado con imagen de referencia",
            "https://srv.latostadora.com/designall.dll/eat_coffee_code_repeat_light--i:135623234096701356232017092620;k:220c090171475e2d3afb83832b2e7b37;h:350;b:f8f8f8;s:H_A20.jpg"
        ),
        Producto(
            2,
            "Camiseta Evolución",
            35000,
            "El Tesla Model X es un SUV completa",
            "https://srv.latostadora.com/designall.dll/programador_de_evolucion--i:13562317602260135623201709261;k:e4118e7320557f49e6563868e7843708;h:350;b:f8f8f8;s:H_A1.jpg"
        ) ,

        Producto(
            3,
            "Camiseta Evolución",
            35000,
            "Camiseta",
            "https://srv.latostadora.com/designall.dll/i_love_python--i:1356239335240135623201709261;k:273f5332a481c3504f65c86acf3cd21c;h:350;b:f8f8f8;s:H_A1.jpg"
        ),
        Producto(
            4,
            "Camiseta Evolución",
            35000,
            "Camiseta",
            imageUrl = "https://srv.latostadora.com/designall.dll/i_love_python--i:1356239335240135623201709261;k:273f5332a481c3504f65c86acf3cd21c;h:350;b:f8f8f8;s:H_A1.jpg"
        )


    )

    fun  getProductos():List<Producto> {

        //todo consultar todos los productos de firebase
        return productos
    }

    fun findByIds(productosIds:List<Int>):List<Producto> {
        //todo consultar todos los productos del carrito firebase
        return productos.filter{ p->productosIds.contains(p.id)}

    }




    }
