package com.example.plataformaremota.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plataformaremota.R
import com.example.plataformaremota.data.entity.Trabalho

class TrabalhoAdapter(
    private var trabalhos: MutableList<Trabalho>
) : RecyclerView.Adapter<TrabalhoAdapter.TrabalhoViewHolder>() {

    class TrabalhoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitulo: TextView = itemView.findViewById(R.id.itemTitulo)
        val txtArea: TextView = itemView.findViewById(R.id.itemArea)
        val txtNivel: TextView = itemView.findViewById(R.id.itemNivel)
        val txtDescricao: TextView = itemView.findViewById(R.id.itemDescricao)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrabalhoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trabalho, parent, false)
        return TrabalhoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrabalhoViewHolder, position: Int) {
        val trabalho = trabalhos[position]
        holder.txtTitulo.text = trabalho.titulo
        holder.txtArea.text = trabalho.area
        holder.txtNivel.text = trabalho.nivel
        holder.txtDescricao.text = trabalho.descricao
    }

    override fun getItemCount(): Int = trabalhos.size

    /**
     * Adiciona um novo trabalho à lista e avisa o RecyclerView para atualizar a tela.
     */
    fun adicionarTrabalho(trabalho: Trabalho) {
        trabalhos.add(0, trabalho) // Adiciona no topo da lista
        notifyItemInserted(0)
    }
}