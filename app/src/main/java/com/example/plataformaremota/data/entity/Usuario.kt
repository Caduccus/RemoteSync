package com.example.plataformaremota.data.entity

import java.io.Serializable

/**
 * Classe simples de Usuário sem banco de dados.
 */
data class Usuario(
    val id: Int = 0,
    val nome: String,
    val email: String,
    val senha: String,
    val profissao: String
) : Serializable