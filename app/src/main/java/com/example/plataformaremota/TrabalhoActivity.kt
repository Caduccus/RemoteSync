package com.example.plataformaremota

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.plataformaremota.data.entity.Trabalho

class TrabalhoActivity : AppCompatActivity() {

    private lateinit var edtTitulo: EditText
    private lateinit var edtDescricao: EditText
    private lateinit var edtCategoria: EditText
    private lateinit var edtSalario: EditText
    private lateinit var edtPrazo: EditText
    private lateinit var autoCompleteArea: AutoCompleteTextView
    private lateinit var rgNivel: RadioGroup
    private lateinit var btnPublicar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trabalho)

        vincularComponentes()
        configurarDropdownArea()

        btnPublicar.setOnClickListener {
            coletarERetornarDados()
        }
    }

    private fun vincularComponentes() {
        edtTitulo = findViewById(R.id.edtTitulo)
        edtDescricao = findViewById(R.id.edtDescricao)
        edtCategoria = findViewById(R.id.edtCategoria)
        edtPrazo = findViewById(R.id.edtPrazo)
        autoCompleteArea = findViewById(R.id.autoCompleteArea)
        rgNivel = findViewById(R.id.rgNivel)
        btnPublicar = findViewById(R.id.btnPublicar)
    }

    private fun configurarDropdownArea() {
        val areas = arrayOf("Tecnologia", "Design", "Atendimento", "Marketing")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, areas)
        autoCompleteArea.setAdapter(adapter)
    }

    private fun coletarERetornarDados() {
        val titulo = edtTitulo.text.toString().trim()
        val descricao = edtDescricao.text.toString().trim()
        val categoria = edtCategoria.text.toString().trim()
        val area = autoCompleteArea.text.toString()
        val salario = edtSalario.text.toString().trim()
        val prazo = edtPrazo.text.toString().trim()

        val idNivel = rgNivel.checkedRadioButtonId
        val nivel = if (idNivel != -1) findViewById<RadioButton>(idNivel).text.toString() else ""

        if (titulo.isEmpty() || area.isEmpty() || nivel.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha os campos obrigatórios!", Toast.LENGTH_SHORT).show()
            return
        }

        // Criamos o objeto Trabalho
        val trabalho = Trabalho(
            titulo = titulo,
            descricao = descricao,
            categoria = categoria,
            area = area,
            nivel = nivel,
            prazo = prazo
        )

        // Preparamos o Intent para devolver o objeto para a MainActivity
        val resultIntent = Intent()
        resultIntent.putExtra("EXTRA_TRABALHO", trabalho)
        setResult(RESULT_OK, resultIntent)
        
        // Finaliza a Activity para voltar
        finish()
    }
}
