package com.example.alee.model

interface FilmeDao {
    fun adicionarFilme(filme: Filme)
    fun obterFilmes(): List <Filme>
}