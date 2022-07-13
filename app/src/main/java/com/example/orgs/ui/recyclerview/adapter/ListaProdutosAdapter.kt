package com.example.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.databinding.ProdutoItemBinding
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
    class ViewHolder(binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val nome = binding.produtoItemNome
        private val descricao = binding.produtoItemDescricao
        private val valor = binding.produtoItemValor

        fun vincula(produto: Produto) {
            nome.text = produto.nome
            descricao.text = produto.descricao
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
        val binding = ProdutoItemBinding.inflate(
            //aqui tenho que colocar exatamente o layout que quero mostrar
            LayoutInflater.from(context), parent, false
        )
        return ViewHolder(binding)
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
