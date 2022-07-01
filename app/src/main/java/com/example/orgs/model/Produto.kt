package com.example.orgs.model

import java.math.BigDecimal

data class Produto(
    val nome: String,
    val descricao: String,
    val valor: BigDecimal //ele mantem uma precisao maior que o double para pontos flutuantes
)
