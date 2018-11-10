package com.bonifacio.lanchonete.model.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

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
        return getQuantidadePorIngrediente(new Ingredientes().ALFACE) > 0
                && getQuantidadePorIngrediente(new Ingredientes().BACON) == 0;
    }
    
    public boolean isMuitaCarne() {
        return getQuantidadePorIngrediente(new Ingredientes().HAMBURGUER_CARNE) >= 3;
    }
    
    public boolean isMuitoQueijo() {
        return getQuantidadePorIngrediente(new Ingredientes().QUEIJO) >= 3;
    }
    
    private Integer getQuantidadePorIngrediente(Ingrediente ingrediente) {
        for (PorcaoIngrediente porcaoIngrediente : lanche.getPorcoesIngredientes()) {
            if (porcaoIngrediente.getIngrediente().getNome().equals(ingrediente.getNome())) {
                return porcaoIngrediente.getQuantidade();
            }
        }
        return 0;
    }
    
    public Double getValorFinalSemDesconto() {
        BigDecimal valorOriginal = BigDecimal.ZERO;
        valorOriginal.setScale(2, RoundingMode.HALF_UP);
        for (PorcaoIngrediente porcaoIngrediente : lanche.getPorcoesIngredientes()) {
            valorOriginal = valorOriginal.add(new BigDecimal(porcaoIngrediente.getIngrediente().getValor()).multiply(new BigDecimal(porcaoIngrediente.getQuantidade())));
        }
        return valorOriginal.doubleValue();
    }
 
    public Double getValorFinalComDesconto() {
        Double valorOriginal = getValorFinalSemDesconto();
        BigDecimal valorComDesconto = new BigDecimal(valorOriginal);
        valorComDesconto.setScale(2, RoundingMode.HALF_UP);
        if (isLight()) {
            Double valorDezPorCento = valorOriginal * new BigDecimal(10).divide(new BigDecimal(100)).doubleValue();
            valorComDesconto = valorComDesconto.subtract(new BigDecimal(valorDezPorCento));
        }
        if (isMuitaCarne()) {
            Integer quantidadeHamburgers = getQuantidadePorIngrediente(new Ingredientes().HAMBURGUER_CARNE);
            BigDecimal valorHamburgerIndividual = new BigDecimal(new Ingredientes().HAMBURGUER_CARNE.getValor());
            int totalHamburgersDescontados = quantidadeHamburgers / 3;
            BigDecimal valorDescontoHamburgers = valorHamburgerIndividual.multiply(new BigDecimal(totalHamburgersDescontados));
            valorComDesconto = valorComDesconto.subtract(valorDescontoHamburgers);
        }        
        if (isMuitoQueijo()) { 
            Integer quantidadeQueijo = getQuantidadePorIngrediente(new Ingredientes().QUEIJO);
            BigDecimal valorQueijoIndividual = new BigDecimal(new Ingredientes().QUEIJO.getValor());
            int totalQueijosDescontados = quantidadeQueijo / 3;
            BigDecimal valorDescontoQueijos = valorQueijoIndividual.multiply(new BigDecimal(totalQueijosDescontados));
            valorComDesconto = valorComDesconto.subtract(valorDescontoQueijos);
        }
        return valorComDesconto.doubleValue();
    }
}