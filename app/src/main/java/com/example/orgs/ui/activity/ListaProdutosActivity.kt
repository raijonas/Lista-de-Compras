package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.dao.ProdutoDao
import com.example.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

//aqui a classe main precisa herdar da Activity do android
//os componentes em android possuem ciclos de vida
//a activity possui um ciclo de vida

class ListaProdutosActivity : AppCompatActivity(R.layout.activity_lista_produtos) {
    //o super é responsável em resolver os "pepinos" do android
    //o override é uma sobrescrita

    //vamos colocar o adapter para criar uma unica vez, e pra isso vamos transformar
    //em uma propriedade e movimentar o dao para cá
    private val buscar = ProdutoDao()
    private val adapter = ListaProdutosAdapter(context = this, produtos = buscar.buscarProduto())

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState) //sempre precisamos chamar um super em ciclos de vida
        //com esse ciclo eu posso executar um código

        //nesse caso, a minha activity é o context, entao uso o this pra dizer que é ela
        Toast.makeText(this, "Bem vindo(a) ao Orgs!", Toast.LENGTH_SHORT).show()

        //aqui estou indicando pra activity que agora teremos uma view na pate visual
        //todas as views que sâo componentes, vao herdar de view
        //val view = View(this)
        //val view = TextView(this)
        //view.setText("Cesta de Frutas")

//        val nome = findViewById<TextView>(R.id.nome)
//        nome.text = "Lista de compras do Wagner"
//        val descricao = findViewById<TextView>(R.id.descricao)
//        descricao.text = "Feijão carioca"
//        val valor = findViewById<TextView>(R.id.valor)
//        valor.text = "R$ 8.30"

        //colocando o recyclerView para não ficar recriando varias vezes para n ter passos a mais
        //desnecessários
        configuraRecyclerView()
        configuraFab()


    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(buscar.buscarProduto())
    }

    private fun configuraFab() {
        //aqui vamos inicializar uma activity por meio dessa, utilizando nosso botão
        val fab = findViewById<FloatingActionButton>(R.id.activity_lista_produto_fab)
        fab.setOnClickListener {
            val intent = Intent(this, FormularioProdutoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configuraRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.activity_lista_produto_recyclerView)
        recyclerView.adapter = adapter
        /*    Produto(nome = "Feijão", descricao = "Feijão doce", BigDecimal(20.00)),
            Produto(nome = "Feijão", descricao = "Feijão Carioca", BigDecimal(25.00)),
            Produto(nome = "Fogazza", descricao = "Só pra mim", BigDecimal(20.00) )
        ))*/
    }

    //o recyclerView exige que vc indique qual o gerenciador do layout, como queremos
    //que os componentes sejam apresentados. A forma 1 é em código:
    //a forma 2 estará em layout
    //recyclerView.layoutManager = LinearLayoutManager(this)
}