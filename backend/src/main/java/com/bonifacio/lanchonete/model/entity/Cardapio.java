package com.bonifacio.lanchonete.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zeehb
 */
public class Cardapio {    
        
    public Lanche X_BACON = new Lanche("X-Bacon", new LancheBuilder()
            .adicionarPorcao(new PorcaoIngrediente(1, new Ingredientes().BACON))
            .adicionarPorcao(new PorcaoIngrediente(1, new Ingredientes().HAMBURGUER_CARNE))
            .adicionarPorcao(new PorcaoIngrediente(1, new Ingredientes().QUEIJO))
            .build());
            
    public Lanche X_BURGER = new Lanche("X-Burger", new LancheBuilder()
            .adicionarPorcao(new PorcaoIngrediente(1, new Ingredientes().HAMBURGUER_CARNE))
            .adicionarPorcao(new PorcaoIngrediente(1, new Ingredientes().QUEIJO))
            .build());
    
    public Lanche X_EGG = new Lanche("X-Egg", new LancheBuilder()
            .adicionarPorcao(new PorcaoIngrediente(1, new Ingredientes().OVO))
            .adicionarPorcao(new PorcaoIngrediente(1, new Ingredientes().HAMBURGUER_CARNE))
            .adicionarPorcao(new PorcaoIngrediente(1, new Ingredientes().QUEIJO))
            .build());
    
    public Lanche X_EGG_BACON = new Lanche("X-Egg Bacon", new LancheBuilder()
            .adicionarPorcao(new PorcaoIngrediente(1, new Ingredientes().OVO))
            .adicionarPorcao(new PorcaoIngrediente(1, new Ingredientes().BACON))
            .adicionarPorcao(new PorcaoIngrediente(1, new Ingredientes().HAMBURGUER_CARNE))
            .adicionarPorcao(new PorcaoIngrediente(1, new Ingredientes().QUEIJO))
            .build());
    
    public List<Lanche> getLanches() {
        List<Lanche> lanches = new ArrayList<>();
        lanches.add(X_BACON);
        lanches.add(X_BURGER);
        lanches.add(X_EGG);
        lanches.add(X_EGG_BACON);
        return lanches;
    }
}
