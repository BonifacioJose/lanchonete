package com.bonifacio.lanchonete.model.entity;

/**
 *
 * @author zeehb
 */
public enum Ingrediente {
    
    ALFACE("Alface", 0.40d),
    BACON("Bacon", 2.00d),
    HAMBURGUER_CARNE("Hamburguer de carne", 3.00d),
    OVO("Ovo", 0.80d),
    QUEIJO("Queijo", 1.50d);
    
    private final String nome;
    private final Double valor;

    private Ingrediente(String nome, Double valor) {
        this.nome = nome;
        this.valor = valor;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public Double getValor() {
        return this.valor;
    }
}
