package com.example.orgs.ui.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.model.Produto
import com.example.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import java.math.BigDecimal

//aqui a classe main precisa herdar da Activity do android
//os componentes em android possuem ciclos de vida
//a activity possui um ciclo de vida

class MainActivity : Activity(){
    //o super é responsável em resolver os "pepinos" do android
    //o override é uma sobrescrita
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


        //podemos colocar o nosso layout aqui dentro do setContentView
        //pra isso vamos usar uma classe especial chamada R. Ela tem acesso a todos os arquivos
        //detro do diretório no res

           setContentView(R.layout.activity_main)
//        val nome = findViewById<TextView>(R.id.nome)
//        nome.text = "Lista de compras do Wagner"
//        val descricao = findViewById<TextView>(R.id.descricao)
//        descricao.text = "Feijão carioca"
//        val valor = findViewById<TextView>(R.id.valor)
//        valor.text = "R$ 8.30"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ListaProdutosAdapter(context = this, produtos = listOf(
            Produto(nome = "Feijão", descricao = "Feião doce", BigDecimal(20.00)),
            Produto(nome = "Feijão", descricao = "Feião Carioca", BigDecimal(25.00))
        ))
        //o recyclerView exige que vc indique qual o gerenciador do layout, como queremos
        //que os componentes sejam apresentados. A forma 1 é em código:
        //a forma 2 estrá em layout
        //recyclerView.layoutManager = LinearLayoutManager(this)
    }
}