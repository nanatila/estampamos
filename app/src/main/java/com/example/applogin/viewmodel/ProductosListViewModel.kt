package com.example.applogin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applogin.model.Producto
import com.example.applogin.model.ProductoRepository

class ProductosListViewModel:ViewModel() {

    var productoRepository:ProductoRepository


    var productosModel =MutableLiveData<List<Producto>>()

    var productoModel =MutableLiveData<Producto>()


    init {
        productoRepository=ProductoRepository()

    }


    fun getProductos() {

        var currentProductoList =productoRepository.getProductos(productosModel)
        //productosModel.postValue(currentProductoList)
    }
    /*fun getProductoByBarCode(codeBar:String){
        productoRepository.getProductoByBarCode(productoModel)
    }*/






}