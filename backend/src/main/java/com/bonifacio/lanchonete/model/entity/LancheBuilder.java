package com.bonifacio.lanchonete.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zeehb
 */
public class LancheBuilder {

    private List<PorcaoIngrediente> porcoesIngredientes;
    
    public LancheBuilder() {
        this.porcoesIngredientes = new ArrayList<>();
    }
    
    public LancheBuilder(List<PorcaoIngrediente> porcoesIngredientes) {
        this.porcoesIngredientes = porcoesIngredientes;
    }

    public LancheBuilder adicionarPorcao(PorcaoIngrediente porcaoIngrediente) {
        boolean possuiIngrediente = false;
        for (PorcaoIngrediente iPorcaoIngrediente : this.porcoesIngredientes) {
            if (iPorcaoIngrediente.getIngrediente().getNome().equals(porcaoIngrediente.getIngrediente().getNome())) {
                possuiIngrediente = true;
                iPorcaoIngrediente.setQuantidade(porcaoIngrediente.getQuantidade() + iPorcaoIngrediente.getQuantidade());
            }
        }
        if (!possuiIngrediente) {
            this.porcoesIngredientes.add(porcaoIngrediente);            
        }
        return this;
    }
    
    public List<PorcaoIngrediente> build() {
        return this.porcoesIngredientes;
    }
}
