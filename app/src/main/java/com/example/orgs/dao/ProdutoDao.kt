package com.example.orgs.dao

import com.example.orgs.model.Produto

class ProdutoDao {

    fun adicionarProduto (produto: Produto){
        produtos.add(produto)
    }

    fun buscarProduto() : List<Produto>{
        //o to list serve para fazer uma copia da nossa lista sem que possam
        //alterar as informações originais, ou seja, vai retornar uma cópia
        return produtos.toList()
    }

    companion object {
        //criamos essa classe para armazenar as informações recebidas e
        //enviar para a nossa lista
        private val produtos = mutableListOf<Produto>()
    }
}