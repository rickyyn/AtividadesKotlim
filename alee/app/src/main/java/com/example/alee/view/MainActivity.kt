package com.example.alee.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.alee.R
import com.example.alee.model.Filme
import com.example.alee.model.FilmeDaoImpl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val edt_titulo = findViewById<EditText>(R.id.edt_titulo)
        val edt_genero = findViewById<EditText>(R.id.edt_genero)
        val btnCadastrar = findViewById<Button>(R.id.btn_enviar)
        val btnNextPage = findViewById<Button>(R.id.btn_next_page)

        btnNextPage.setOnClickListener {
            val intent = Intent(this, activity_filmes::class.java)
            startActivity(intent)
        }

        btnCadastrar.setOnClickListener {
            val titulo = edt_titulo.text.toString()
            val genero = edt_genero.text.toString()

            if (titulo.isNotEmpty() && genero.isNotEmpty()) {
                val dao = FilmeDaoImpl()
                dao.adicionarFilme(Filme(titulo, genero, false, 0.0))
                Toast.makeText(this, "Cadastrado", Toast.LENGTH_SHORT).show()
                edt_titulo.text.clear()
                edt_genero.text.clear()
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}