package com.example.applogin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applogin.model.Producto
import com.example.applogin.model.ProductoRepository

class ProductosListViewModel:ViewModel() {

    var productoRepository:ProductoRepository


    var productosModel =MutableLiveData<List<Producto>>()


    init {
        productoRepository=ProductoRepository()

    }


    fun getProductos() {

        var currentProductoList =productoRepository.getProductos()
        productosModel.postValue(currentProductoList)




    }






}