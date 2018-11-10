package com.bonifacio.lanchonete.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author zeehb
 */
public class Lanche implements Serializable {
        
    private String nome;
    private List<PorcaoIngrediente> porcoesIngredientes;
        
    public Lanche() {        
    }

    public Lanche(String nome, List<PorcaoIngrediente> porcoesIngredientes) {
        this.nome = nome;
        this.porcoesIngredientes = porcoesIngredientes;
    }

    public String getNome() {
        return nome;
    }

    public List<PorcaoIngrediente> getPorcoesIngredientes() {
        return porcoesIngredientes;
    }
    
    public void setPorcoesIngredientes(List<PorcaoIngrediente> porcoesIngredientes) {
        this.porcoesIngredientes = porcoesIngredientes;
    }
    
    public Double getValor() {
        return new PromocaoChecker(this).getValorFinalComDesconto();
    }    
    
    public Double getValorOriginal() {
        return new PromocaoChecker(this).getValorFinalSemDesconto();
    }
}
