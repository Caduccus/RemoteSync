package com.example.plataformaremota

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformaremota.adapter.TrabalhoAdapter
import com.example.plataformaremota.data.entity.Trabalho

class MainActivity : AppCompatActivity() {

    private lateinit var txtBoasVindas: TextView
    private lateinit var btnNovoTrabalho: Button
    private lateinit var recyclerTrabalhos: RecyclerView
    private lateinit var adapter: TrabalhoAdapter

    // 1. Criamos uma lista na memória para guardar as vagas durante a execução do app
    private val listaTrabalhos = mutableListOf<Trabalho>()

    // 2. Configuramos o "escutador" que vai esperar a TrabalhoActivity devolver uma nova vaga
    private val trabalhoLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // Pegamos o objeto Trabalho que foi enviado de volta
            val trabalho = result.data?.getSerializableExtra("EXTRA_TRABALHO") as? Trabalho
            trabalho?.let {
                // Adicionamos na nossa lista e avisamos o adapter para mostrar na tela
                adapter.adicionarTrabalho(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtBoasVindas = findViewById(R.id.txtBoasVindas)
        btnNovoTrabalho = findViewById(R.id.btnNovoTrabalho)
        recyclerTrabalhos = findViewById(R.id.recyclerTrabalhos)

        val nomeUsuario = intent.getStringExtra("nomeUsuario") ?: "Usuário"
        txtBoasVindas.text = "Olá, $nomeUsuario!"

        configurarRecyclerView()

        btnNovoTrabalho.setOnClickListener {
            val intent = Intent(this, TrabalhoActivity::class.java)
            // 3. Iniciamos a tela usando o launcher em vez do startActivity comum
            trabalhoLauncher.launch(intent)
        }
    }

    private fun configurarRecyclerView() {
        // Iniciamos o adapter com a nossa lista de memória
        adapter = TrabalhoAdapter(listaTrabalhos)
        recyclerTrabalhos.layoutManager = LinearLayoutManager(this)
        recyclerTrabalhos.adapter = adapter
    }
}