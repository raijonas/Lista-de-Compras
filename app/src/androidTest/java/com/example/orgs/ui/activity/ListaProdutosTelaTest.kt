package com.example.orgs.ui.activity

import android.support.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class ListaProdutosTelaTest{
    @Rule
    public var activity =  ActivityTestRule(ListaProdutosActivity::class.java, true, true )

    @Test
    fun deve_aparecerUmProduto_quandoCadastrarUm(){
        //Esse metodo (onView) busca a view que queremos uma verificação
        //nele mandamos um metodo para verificar se ela está presente
        //o withText é um comportamento que ajuda a verificar uma view com um texto
        //o retorno será uma interação, o check ajuda a fazer verificações
        //dentro do chek fazemos um Assert
        onView(withText("Cebola"))
            .check(matches(isDisplayed()))
    }
}