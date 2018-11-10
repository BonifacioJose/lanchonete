package com.bonifacio.lanchonete.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;

/**
 *
 * @author zeehb
 */
public class Ingrediente implements Serializable {
    
    private String nome;
    private Double valor;

    public Ingrediente() {
    }    
    
    public Ingrediente(String nome, Double valor) {
        this.nome = nome;
        this.valor = valor;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public Double getValor() {
        return this.valor;
    }
    
    public void setValor(Double valor) {
        this.valor = valor;
    }

}
