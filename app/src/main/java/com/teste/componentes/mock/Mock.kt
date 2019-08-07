package com.teste.componentes.mock

class Mock private constructor(){

    companion object {
        fun getList(): List<String> = listOf("Gramas", "Kg", "Arroba", "Toneladas")
    }
}