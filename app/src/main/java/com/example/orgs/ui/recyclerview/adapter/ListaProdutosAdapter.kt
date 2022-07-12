package com.example.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.model.Produto

//aqui precisamos criar uma colection na ListaProdutosAdapter pq as funcs abaixos preccisa de uma lista
class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    //essa propriedade é uma lista modificavel que vai ajudar a modificar
    //os produtos dentro do nosso adapter no lugar de uma lista fisica

    private val produtos = produtos.toMutableList() //serve para transformar em uma lista multavel

    //classe para a onCreateViewHolder
    //ele sempre retorna o view holder que estamos criando
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun vincula(produto: Produto) {
            val nome = itemView.findViewById<TextView>(R.id.produto_item_nome)
            nome.text = produto.nome
            val descricao = itemView.findViewById<TextView>(R.id.produto_item_descricao)
            descricao.text = produto.descricao
            val valor = itemView.findViewById<TextView>(R.id.produto_item_valor)
            valor.text = produto.valor.toPlainString()
        }
    }

    /*Basicamente o próprio nome diz que ele vai criar essa referência chamada de ViewHolder.
    O grande detalhe é o que é o ViewHolder. O ViewHolder vai ser o responsável em pegar cada uma
    dessas views que são apresentadas aqui e fazer o processo de binding.*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //vamos criar uma view exclusiva a partir do nosso layout
        //ele vai pedir um contexto, mas como adapter não tem um contexto
        //vamos criar um atributo no construtor e usar como parametro
        val inflater = LayoutInflater.from(context)
        //aqui tenho que colocar exatamente o layout que quero mostrar
        val view = inflater.inflate(R.layout.produto_item, parent, false)
        return ViewHolder(view)
    }


    //ele vai indicar qual o item que estamos naquele momento, qual a posição dele
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    //quantos itens queremos apresentar no adapter
    override fun getItemCount(): Int = produtos.size


    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear() //o clear serve para limpar os dados que eu quero alterar
        this.produtos.addAll(produtos)
        notifyDataSetChanged() //ele avisa paa o adpater que fizemos uma modificação
        //assim, o adpter vai recarregar com as novas infos
    }

}
