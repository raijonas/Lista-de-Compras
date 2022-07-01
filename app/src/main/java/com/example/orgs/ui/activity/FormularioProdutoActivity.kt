package com.example.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.orgs.R
import com.example.orgs.dao.ProdutoDao
import com.example.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {
    //com o androidX não precisamos do setContentview e nem da fun onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //configurar o botao para salvar
        val botaoSalvar = findViewById<Button>(R.id.salvarButton)


        //listeners são referencias que ficam atentas a alguma ação
        //nesse caso será para o clique no nosso botao
        //geralmente essas listeners são para capturar ações
        //nesse caso o onClickLister vem de qualquer view
        //esse primeiro é de interface
        botaoSalvar.setOnClickListener {
            val campoNome = findViewById<EditText>(R.id.nome)
            //buscamos as informações de uma edit text assim, usando o text e toString()
            val nome = campoNome.text.toString()

            val campoDescricao = findViewById<EditText>(R.id.descricao)
            val descricao = campoDescricao.text.toString()

            val campoValor = findViewById<EditText>(R.id.valor)
            val valorEmTexto = campoValor.text.toString()
            //se o valor preenchido estiver vazio, ele vai retornar zero
            val valor = if (valorEmTexto.isBlank()) {
                BigDecimal.ZERO
            } else {
                BigDecimal(valorEmTexto)
            }

            val produtoNovo = Produto(
                nome = nome,
                descricao = descricao,
                valor = valor
            )
            //vamos colocar logs para retornar e saber se esta dando certo
            //a primeira é uma chave que vc escolhe, depois o valor que espera e a variavel
            //lembrando que nao pode ser mais que 23 caracteres
            Log.i("FormularioProdutoAct", "onCreate: $produtoNovo")


            val dao = ProdutoDao()
            dao.adicionarProduto(produtoNovo)
            Log.i("FormularioProdutoAct", "onCreate: ${dao.buscarProduto()}")


        }
    }
}