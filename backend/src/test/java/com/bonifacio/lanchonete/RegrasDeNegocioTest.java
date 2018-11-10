package com.bonifacio.lanchonete;

import com.bonifacio.lanchonete.model.entity.Cardapio;
import com.bonifacio.lanchonete.model.entity.Ingrediente;
import com.bonifacio.lanchonete.model.entity.Ingredientes;
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
public class RegrasDeNegocioTest {

    @Test
    public void testValorLanchesCardapio() {
        Double valorXBacon = 0d;
        valorXBacon += new Ingredientes().BACON.getValor();
        valorXBacon += new Ingredientes().HAMBURGUER_CARNE.getValor();
        valorXBacon += new Ingredientes().QUEIJO.getValor();
        Assert.assertEquals(valorXBacon, new Cardapio().X_BACON.getValor());
        
        Double valorXBurger = 0d;
        valorXBurger += new Ingredientes().HAMBURGUER_CARNE.getValor();
        valorXBurger += new Ingredientes().QUEIJO.getValor();
        Assert.assertEquals(valorXBurger, new Cardapio().X_BURGER.getValor());
        
        Double valorXEgg = 0d;
        valorXEgg += new Ingredientes().OVO.getValor();
        valorXEgg += new Ingredientes().HAMBURGUER_CARNE.getValor();
        valorXEgg += new Ingredientes().QUEIJO.getValor();
        Assert.assertEquals(valorXEgg, new Cardapio().X_EGG.getValor());
        
        Double valorXEggBacon = 0d;
        valorXEggBacon += new Ingredientes().OVO.getValor();
        valorXEggBacon += new Ingredientes().HAMBURGUER_CARNE.getValor();
        valorXEggBacon += new Ingredientes().BACON.getValor();
        valorXEggBacon += new Ingredientes().QUEIJO.getValor();
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
        valorIngredientes += new Ingredientes().HAMBURGUER_CARNE.getValor();
        valorIngredientes += new Ingredientes().QUEIJO.getValor();
        Assert.assertEquals(valorIngredientes, xBurger.getValorOriginal());
        setLight(xBurger);
        valorIngredientes += new Ingredientes().ALFACE.getValor();
        Double valorDezPorCento = valorIngredientes * new BigDecimal(10).divide(new BigDecimal(100)).doubleValue();
        Double valorComDesconto = valorIngredientes - valorDezPorCento;
        Assert.assertEquals(valorComDesconto, xBurger.getValor());
    }

    private void setLight(Lanche lanche) {
        lanche.setPorcoesIngredientes(
                new LancheBuilder(lanche.getPorcoesIngredientes())
                        .adicionarPorcao(new PorcaoIngrediente(1, new Ingredientes().ALFACE))
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
        valorIngredientes += new Ingredientes().HAMBURGUER_CARNE.getValor();
        valorIngredientes += new Ingredientes().QUEIJO.getValor();
        Assert.assertEquals(valorIngredientes, xBurger.getValorOriginal());
        setMuitaCarne(xBurger);
        valorIngredientes += new Ingredientes().HAMBURGUER_CARNE.getValor();
        Assert.assertEquals(valorIngredientes, xBurger.getValor());
    }

    private void setMuitaCarne(Lanche lanche) {
        lanche.setPorcoesIngredientes(
                new LancheBuilder(lanche.getPorcoesIngredientes())
                        .adicionarPorcao(new PorcaoIngrediente(2, new Ingredientes().HAMBURGUER_CARNE))
                        .build());
    }

    @Test
    public void testIsMuitoQueijo() {
        Lanche xBurger = new Cardapio().X_BURGER;
        Assert.assertFalse(new PromocaoChecker(xBurger).isMuitoQueijo());
        setMuitoQueijo(xBurger);
        PromocaoChecker promocaoChecker = new PromocaoChecker(xBurger);
        Assert.assertTrue(promocaoChecker.isMuitoQueijo());
    }

    @Test
    public void testPrecoMuitoQueijo() {
        Lanche xBurger = new Cardapio().X_BURGER;
        Double valorIngredientes = 0d;
        valorIngredientes += new Ingredientes().HAMBURGUER_CARNE.getValor();
        valorIngredientes += new Ingredientes().QUEIJO.getValor();
        Assert.assertEquals(valorIngredientes, xBurger.getValorOriginal());
        setMuitoQueijo(xBurger);
        valorIngredientes += new Ingredientes().QUEIJO.getValor();
        Assert.assertEquals(valorIngredientes, xBurger.getValor());
    }

    private void setMuitoQueijo(Lanche lanche) {
        lanche.setPorcoesIngredientes(
                new LancheBuilder(lanche.getPorcoesIngredientes())
                        .adicionarPorcao(new PorcaoIngrediente(2, new Ingredientes().QUEIJO))
                        .build());
    }

}
