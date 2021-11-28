package com.example.applogin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applogin.model.Producto
import com.example.applogin.model.ProductoRepository

class CarritoListViewModel:ViewModel() {

    var productoRepository:ProductoRepository
    var productosModel =MutableLiveData<List<Producto>>()


    init {
        productoRepository=ProductoRepository()

    }

    fun getProductosByIds(productosIds:List<Int>) {

        var currentProductoList =productoRepository.findByIds(productosIds)
        productosModel.postValue(currentProductoList)

    }






}