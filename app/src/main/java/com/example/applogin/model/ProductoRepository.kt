package com.example.applogin.model

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProductoRepository {

    val db = Firebase.firestore
    val docRef = db.collection("productos")

    private var productos = listOf<Producto>()

    /*

        Producto(
            "1",
            "Camiseta Code ",
            35000,
            "Camiseta Negra Estampado con imagen de referencia",
            "https://srv.latostadora.com/designall.dll/eat_coffee_code_repeat_light--i:135623234096701356232017092620;k:220c090171475e2d3afb83832b2e7b37;h:350;b:f8f8f8;s:H_A20.jpg"
        ),
        Producto(
            "2",
            "Camiseta Evolución",
            35000,
            "El Tesla Model X es un SUV completa",
            "https://srv.latostadora.com/designall.dll/programador_de_evolucion--i:13562317602260135623201709261;k:e4118e7320557f49e6563868e7843708;h:350;b:f8f8f8;s:H_A1.jpg"
        ) ,

        Producto(
            "3",
            "Camiseta Evolución",
            35000,
            "Camiseta",
            "https://srv.latostadora.com/designall.dll/i_love_python--i:1356239335240135623201709261;k:273f5332a481c3504f65c86acf3cd21c;h:350;b:f8f8f8;s:H_A1.jpg"
        ),
        Producto(
            "4",
            "Camiseta Evolución",
            35000,
            "Camiseta",
            imageUrl = "https://srv.latostadora.com/designall.dll/i_love_python--i:1356239335240135623201709261;k:273f5332a481c3504f65c86acf3cd21c;h:350;b:f8f8f8;s:H_A1.jpg"
        )


    )
*/
    fun getProductos(mutableLiveData: MutableLiveData<List<Producto>>) {

        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && !snapshot.isEmpty) {

                productos = listOf()
                for (document in snapshot.documents) {

                    var producto = document.toObject(Producto::class.java)

                    if (producto != null) {
                        producto.id = document.id
                        productos += producto
                    }

                }
                mutableLiveData.postValue(productos)

            } else {
                Log.d(TAG, "Current data: null")
            }
        }


    }

    fun findByIds(
        productosIds: List<String>, mutableLiveData: MutableLiveData<List<Producto>>): List<Producto> {

        println(">>> IDs de los Productos")
        println(productosIds)

        var productosFilter: List<Producto> = mutableListOf<Producto>()

        if (!productosIds.isEmpty()) {

            docRef.whereIn(FieldPath.documentId(), productosIds).addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e)
                        return@addSnapshotListener
                    }

                    if (snapshot != null && !snapshot.isEmpty) {

                        productosFilter = listOf()
                        for (document in snapshot.documents) {

                            var producto = document.toObject(Producto::class.java)

                            if (producto != null) {
                                producto.id = document.id
                                productosFilter += producto
                            }

                        }

                        mutableLiveData.postValue(productosFilter)

                    } else {
                        Log.d(TAG, "Current data: null")
                    }
                }


        }

        return productosFilter
    }

    fun decrementarInventario() {
        //TODO Decrementar Inventario en Firebase

    }
    fun getProductoByBarCode( mutableLiveData: MutableLiveData<Producto>, codeBar:String){
        //TODO traer productos de firebase
        var producto=Producto(
            "1",
            "Camiseta Code ",
            35000,
            "Camiseta Negra Estampado con imagen de referencia",
            "https://srv.latostadora.com/designall.dll/eat_coffee_code_repeat_light--i:135623234096701356232017092620;k:220c090171475e2d3afb83832b2e7b37;h:350;b:f8f8f8;s:H_A20.jpg",
            5,
            "ABC-1001",
        )

        docRef.whereEqualTo("cod_barras", codeBar).addSnapshotListener() { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && !snapshot.isEmpty) {

                for (document in snapshot.documents) {

                    if(producto!=null){
                        producto = document.toObject(Producto::class.java)!!
                    }

                }
                mutableLiveData.postValue(producto)

            } else {
                Log.d(TAG, "Current data: null")
            }
        }

        mutableLiveData.postValue(producto)



    }
}
