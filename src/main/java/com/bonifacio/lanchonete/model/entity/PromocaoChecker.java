package com.bonifacio.lanchonete.model.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author zeehb
 */
public class PromocaoChecker {
    
    private final Lanche lanche;
    
    public PromocaoChecker(Lanche lanche) {
        this.lanche = lanche;
    }
    
    public boolean isLight() {
        return getQuantidadePorIngrediente(Ingrediente.ALFACE) > 0
                && getQuantidadePorIngrediente(Ingrediente.BACON) == 0;
    }
    
    public boolean isMuitaCarne() {
        return getQuantidadePorIngrediente(Ingrediente.HAMBURGUER_CARNE) >= 3;
    }
    
    public boolean isMuitoQueijo() {
        return getQuantidadePorIngrediente(Ingrediente.QUEIJO) >= 3;
    }
    
    private Integer getQuantidadePorIngrediente(Ingrediente ingrediente) {
        for (PorcaoIngrediente porcaoIngrediente : lanche.getPorcoesIngredientes()) {
            if (porcaoIngrediente.getIngrediente().equals(ingrediente)) {
                return porcaoIngrediente.getQuantidade();
            }
        }
        return 0;
    }
    
    public Double getValorFinalSemDesconto() {
        Double valorOriginal = 0d;
        for (PorcaoIngrediente porcaoIngrediente : lanche.getPorcoesIngredientes()) {
            valorOriginal += porcaoIngrediente.getIngrediente().getValor() * porcaoIngrediente.getQuantidade();
        }
        return valorOriginal;
    }
 
    public Double getValorFinalComDesconto() {
        Double valorOriginal = getValorFinalSemDesconto();
        Double valorComDesconto = valorOriginal;
        if (isLight()) {
            Double valorDezPorCento = valorOriginal * new BigDecimal(10).divide(new BigDecimal(100)).doubleValue();
            valorComDesconto -= valorDezPorCento;
        }
        if (isMuitaCarne()) {
            valorComDesconto -= new BigDecimal(Ingrediente.HAMBURGUER_CARNE.getValor())
                     .multiply(new BigDecimal(getQuantidadePorIngrediente(Ingrediente.HAMBURGUER_CARNE) / 3)).doubleValue();
        }        
        if (isMuitoQueijo()) {
            valorComDesconto -= new BigDecimal(Ingrediente.QUEIJO.getValor())
                    .multiply(new BigDecimal(getQuantidadePorIngrediente(Ingrediente.QUEIJO) / 3)).doubleValue();
        }
        return valorComDesconto;
    }
}