package com.example.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import coil.load
import com.example.orgs.R
import com.example.orgs.dao.ProdutoDao
import com.example.orgs.databinding.ActivityFormularioProdutoBinding
import com.example.orgs.databinding.FormularioImagemBinding
import com.example.orgs.model.Produto
import com.example.orgs.ui.Dialog.FormularioImagemDioalog
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {
    //com o androidX não precisamos do setContentview e nem da fun onCreate
    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //configurar o botao para salvar
        configuraBotaoSalvar()
        setContentView(binding.root)
        binding.activityFormularioProdutoImagem.setOnClickListener {
            FormularioImagemDioalog(this)
                .mostra(url){ imagem ->
                    url = imagem
                    binding.activityFormularioProdutoImagem.load(url)
                }//essa expressão lambida vem de formularioImagemDialog, que vai
                //ser executada no momento que desejar la em formularioImagemDialog
        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioProdutoSalvarButton
        val dao = ProdutoDao()

        //listeners são referencias que ficam atentas a alguma ação
        //nesse caso será para o clique no nosso botao
        //geralmente essas listeners são para capturar ações
        //nesse caso o onClickLister vem de qualquer view
        //esse primeiro é de interface
        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            //vamos colocar logs para retornar e saber se esta dando certo
            //a primeira é uma chave que vc escolhe, depois o valor que espera e a variavel
            //lembrando que nao pode ser mais que 23 caracteres
            Log.i("FormularioProdutoAct", "onCreate: $produtoNovo")



            dao.adicionarProduto(produtoNovo)
            Log.i("FormularioProdutoAct", "onCreate: ${dao.buscarProduto()}")
            finish() //esse método finaliza nossa activity e volta para a anterior


        }
    }

    private fun criaProduto(): Produto {
        val campoNome = binding.activityFormularioProdutoNome
        //buscamos as informações de uma edit text assim, usando o text e toString()
        val nome = campoNome.text.toString()

        val campoDescricao = binding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()

        val campoValor = binding.activityFormularioProdutoValor
        val valorEmTexto = campoValor.text.toString()
        //se o valor preenchido estiver vazio, ele vai retornar zero
        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produto(
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url
        )
    }
}