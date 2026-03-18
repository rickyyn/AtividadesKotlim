package com.example.alee.model

data class Filme (val titulo : String = "", val genero: String = "",
                  var assistido: Boolean = false, // Novo campo
                  var nota: Double = 0.0)