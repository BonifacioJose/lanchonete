package com.bonifacio.lanchonete;

import com.bonifacio.lanchonete.model.entity.Cardapio;
import com.bonifacio.lanchonete.model.entity.Ingrediente;
import com.bonifacio.lanchonete.model.entity.Lanche;
import com.bonifacio.lanchonete.model.entity.LancheBuilder;
import com.bonifacio.lanchonete.model.entity.PorcaoIngrediente;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LanchoneteApplication {

    public static void main(String[] args) {
        SpringApplication.run(LanchoneteApplication.class, args);
        Lanche xBurgerComSalada = new Cardapio().X_BURGER;
        xBurgerComSalada.setPorcoesIngredientes(
                new LancheBuilder(xBurgerComSalada.getPorcoesIngredientes())
                        .adicionarPorcao(new PorcaoIngrediente(6, Ingrediente.QUEIJO))
                        .build());
        System.out.println(xBurgerComSalada.getValor());
    }
}
