package com.example.alee.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.alee.R
import com.example.alee.model.Filme

class FilmeAdapter(private val filmes: List<Filme>) :
    RecyclerView.Adapter<FilmeAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitulo: TextView = itemView.findViewById(R.id.textView_tituloFilme)
        val textViewGenero: TextView = itemView.findViewById(R.id.textView_generoFilme)
        val cbAssistido: CheckBox = itemView.findViewById(R.id.cb_assistido)
        val textViewNota: TextView = itemView.findViewById(R.id.textView_notaFilme)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_filme, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filme = filmes[position]
        holder.textViewTitulo.text = filme.titulo
        holder.textViewGenero.text = filme.genero
        holder.cbAssistido.isChecked = filme.assistido

        if (filme.assistido) {
            holder.textViewNota.visibility = View.VISIBLE
            holder.textViewNota.text = "Nota: ${filme.nota}"
        } else {
            holder.textViewNota.visibility = View.GONE
        }

        holder.cbAssistido.setOnClickListener {
            val isChecked = holder.cbAssistido.isChecked
            filme.assistido = isChecked

            if (isChecked) {
                val context = holder.itemView.context
                val input = EditText(context)
                input.hint = "Digite a nota (0.0 a 10.0)"

                AlertDialog.Builder(context)
                    .setTitle("Nota para ${filme.titulo}")
                    .setView(input)
                    .setPositiveButton("Salvar") { _, _ ->
                        filme.nota = input.text.toString().toDoubleOrNull() ?: 0.0
                        notifyItemChanged(position)
                    }
                    .setNegativeButton("Cancelar") { _, _ ->
                        holder.cbAssistido.isChecked = false
                        filme.assistido = false
                    }
                    .show()
            } else {
                filme.nota = 0.0
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount() = filmes.size
}