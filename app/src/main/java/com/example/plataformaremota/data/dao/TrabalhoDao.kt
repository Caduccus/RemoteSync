package com.example.plataformaremota.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.plataformaremota.data.entity.Trabalho

@Dao
interface TrabalhoDao {

    // 1. Removido o 'suspend' para permitir chamadas síncronas dentro de Threads comuns
    @Insert
    fun inserirTrabalho(trabalho: Trabalho)

    @Query("SELECT * FROM trabalhos ORDER BY id DESC")
    fun listarTodos(): List<Trabalho>
}