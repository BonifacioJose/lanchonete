package com.bonifacio.lanchonete.model.entity;

import java.util.Objects;

/**
 *
 * @author zeehb
 */
public class PorcaoIngrediente {
    
    private Integer quantidade;
    private final Ingrediente ingrediente;

    public PorcaoIngrediente(Integer quantidade, Ingrediente ingrediente) {
        this.quantidade = quantidade;
        this.ingrediente = ingrediente;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.ingrediente);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PorcaoIngrediente other = (PorcaoIngrediente) obj;
        if (this.ingrediente != other.ingrediente) {
            return false;
        }
        return true;
    }
    
    
    
}
