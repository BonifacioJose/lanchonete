package com.bonifacio.lanchonete;

import com.bonifacio.lanchonete.model.entity.Cardapio;
import com.bonifacio.lanchonete.model.entity.Ingrediente;
import com.bonifacio.lanchonete.model.entity.Lanche;
import com.bonifacio.lanchonete.model.entity.LancheBuilder;
import com.bonifacio.lanchonete.model.entity.PorcaoIngrediente;
import com.bonifacio.lanchonete.model.entity.PromocaoChecker;
import java.math.BigDecimal;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LanchoneteApplicationTests {

    @Test
    public void testValorLanchesCardapio() {
        Double valorXBacon = 0d;
        valorXBacon += Ingrediente.BACON.getValor();
        valorXBacon += Ingrediente.HAMBURGUER_CARNE.getValor();
        valorXBacon += Ingrediente.QUEIJO.getValor();
        Assert.assertEquals(valorXBacon, new Cardapio().X_BACON.getValor());
        
        Double valorXBurger = 0d;
        valorXBurger += Ingrediente.HAMBURGUER_CARNE.getValor();
        valorXBurger += Ingrediente.QUEIJO.getValor();
        Assert.assertEquals(valorXBurger, new Cardapio().X_BURGER.getValor());
        
        Double valorXEgg = 0d;
        valorXEgg += Ingrediente.OVO.getValor();
        valorXEgg += Ingrediente.HAMBURGUER_CARNE.getValor();
        valorXEgg += Ingrediente.QUEIJO.getValor();
        Assert.assertEquals(valorXEgg, new Cardapio().X_EGG.getValor());
        
        Double valorXEggBacon = 0d;
        valorXEggBacon += Ingrediente.OVO.getValor();
        valorXEggBacon += Ingrediente.HAMBURGUER_CARNE.getValor();
        valorXEggBacon += Ingrediente.BACON.getValor();
        valorXEggBacon += Ingrediente.QUEIJO.getValor();
        Assert.assertEquals(valorXEggBacon, new Cardapio().X_EGG_BACON.getValor());
    }

    @Test
    public void testIsLight() {
        Lanche xBurger = new Cardapio().X_BURGER;
        Assert.assertFalse(new PromocaoChecker(xBurger).isLight());
        setLight(xBurger);
        Assert.assertTrue(new PromocaoChecker(xBurger).isLight());
    }

    @Test
    public void testPrecoLight() {
        Lanche xBurger = new Cardapio().X_BURGER;
        Double valorIngredientes = 0d;
        valorIngredientes += Ingrediente.HAMBURGUER_CARNE.getValor();
        valorIngredientes += Ingrediente.QUEIJO.getValor();
        Assert.assertEquals(valorIngredientes, xBurger.getValorOriginal());
        setLight(xBurger);
        valorIngredientes += Ingrediente.ALFACE.getValor();
        Double valorDezPorCento = valorIngredientes * new BigDecimal(10).divide(new BigDecimal(100)).doubleValue();
        Double valorComDesconto = valorIngredientes - valorDezPorCento;
        Assert.assertEquals(valorComDesconto, xBurger.getValor());
    }

    private void setLight(Lanche lanche) {
        lanche.setPorcoesIngredientes(
                new LancheBuilder(lanche.getPorcoesIngredientes())
                        .adicionarPorcao(new PorcaoIngrediente(1, Ingrediente.ALFACE))
                        .build());
    }

    @Test
    public void testIsMuitaCarne() {
        Lanche xBurger = new Cardapio().X_BURGER;
        Assert.assertFalse(new PromocaoChecker(xBurger).isMuitaCarne());
        setMuitaCarne(xBurger);
        Assert.assertTrue(new PromocaoChecker(xBurger).isMuitaCarne());
    }

    @Test
    public void testPrecoMuitaCarne() {
        Lanche xBurger = new Cardapio().X_BURGER;
        Double valorIngredientes = 0d;
        valorIngredientes += Ingrediente.HAMBURGUER_CARNE.getValor();
        valorIngredientes += Ingrediente.QUEIJO.getValor();
        Assert.assertEquals(valorIngredientes, xBurger.getValorOriginal());
        setMuitaCarne(xBurger);
        valorIngredientes += Ingrediente.HAMBURGUER_CARNE.getValor();
        Assert.assertEquals(valorIngredientes, xBurger.getValor());
    }

    private void setMuitaCarne(Lanche lanche) {
        lanche.setPorcoesIngredientes(
                new LancheBuilder(lanche.getPorcoesIngredientes())
                        .adicionarPorcao(new PorcaoIngrediente(2, Ingrediente.HAMBURGUER_CARNE))
                        .build());
    }

    @Test
    public void testIsMuitoQueijo() {
        Lanche xBurger = new Cardapio().X_BURGER;
        Assert.assertFalse(new PromocaoChecker(xBurger).isMuitoQueijo());
        setMuitoQueijo(xBurger);
        Assert.assertTrue(new PromocaoChecker(xBurger).isMuitoQueijo());
    }

    @Test
    public void testPrecoMuitoQueijo() {
        Lanche xBurger = new Cardapio().X_BURGER;
        Double valorIngredientes = 0d;
        valorIngredientes += Ingrediente.HAMBURGUER_CARNE.getValor();
        valorIngredientes += Ingrediente.QUEIJO.getValor();
        Assert.assertEquals(valorIngredientes, xBurger.getValorOriginal());
        setMuitoQueijo(xBurger);
        valorIngredientes += Ingrediente.QUEIJO.getValor();
        Assert.assertEquals(valorIngredientes, xBurger.getValor());
    }

    private void setMuitoQueijo(Lanche lanche) {
        lanche.setPorcoesIngredientes(
                new LancheBuilder(lanche.getPorcoesIngredientes())
                        .adicionarPorcao(new PorcaoIngrediente(2, Ingrediente.QUEIJO))
                        .build());
    }

}
