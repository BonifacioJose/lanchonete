package com.bonifacio.lanchonete.model.entity;

/**
 *
 * @author zeehb
 */
public class Cardapio {    
    
    public Lanche X_CUSTOMIZAVEL = new Lanche("X-Customizável", new LancheBuilder()
            .build());
    
    public Lanche X_BACON = new Lanche("X-Bacon", new LancheBuilder()
            .adicionarPorcao(new PorcaoIngrediente(1, Ingrediente.BACON))
            .adicionarPorcao(new PorcaoIngrediente(1, Ingrediente.HAMBURGUER_CARNE))
            .adicionarPorcao(new PorcaoIngrediente(1, Ingrediente.QUEIJO))
            .build());
            
    public Lanche X_BURGER = new Lanche("X-Burger", new LancheBuilder()
            .adicionarPorcao(new PorcaoIngrediente(1, Ingrediente.HAMBURGUER_CARNE))
            .adicionarPorcao(new PorcaoIngrediente(1, Ingrediente.QUEIJO))
            .build());
    
    public Lanche X_EGG = new Lanche("X-Egg", new LancheBuilder()
            .adicionarPorcao(new PorcaoIngrediente(1, Ingrediente.OVO))
            .adicionarPorcao(new PorcaoIngrediente(1, Ingrediente.HAMBURGUER_CARNE))
            .adicionarPorcao(new PorcaoIngrediente(1, Ingrediente.QUEIJO))
            .build());
    
    public Lanche X_EGG_BACON = new Lanche("X-Egg Bacon", new LancheBuilder()
            .adicionarPorcao(new PorcaoIngrediente(1, Ingrediente.OVO))
            .adicionarPorcao(new PorcaoIngrediente(1, Ingrediente.BACON))
            .adicionarPorcao(new PorcaoIngrediente(1, Ingrediente.HAMBURGUER_CARNE))
            .adicionarPorcao(new PorcaoIngrediente(1, Ingrediente.QUEIJO))
            .build());
    
}
