package com.example.orgs.ui.Dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import coil.load
import com.example.orgs.R
import com.example.orgs.databinding.FormularioImagemBinding

class FormularioImagemDioalog(private val context: Context) {

    //aqui trabalhamos com o tipo função
    //nesse caso aqui ela é do tipo unity

    fun mostra(urlPadrao: String? = null, quandoImagemCarregada: (imagem: String) -> Unit) {

        FormularioImagemBinding
            .inflate(LayoutInflater.from(context)).apply {

                urlPadrao?.let {
                    formularioImagemImagemview.load(it)
                    formularioImagemUrl.setText(it)
                }

                formularioImagemBotaoCarregar.setOnClickListener {
                    val url = formularioImagemUrl.text.toString()
                    formularioImagemImagemview.load(url) {
                        error(R.drawable.erro)
                        //enquanto estiver carregando, vai mostrar essa imagem
                        placeholder(R.drawable.placeholder)
                    }
                }

                AlertDialog.Builder(context)
                    .setView(root)
                    .setPositiveButton("Confirmar") { _, _ ->
                        val url = formularioImagemUrl.text.toString()
                        quandoImagemCarregada(url)

                    }
                    .setNegativeButton("Cancelar") { _, _ -> }
                    .show()
            }
    }
}