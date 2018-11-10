package com.bonifacio.lanchonete;

import com.bonifacio.lanchonete.model.entity.Cardapio;
import com.bonifacio.lanchonete.model.entity.Ingredientes;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author zeehb
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InflacaoTest {

    @Test
    public void testMudancaValorIngrediente() {        
        Double valorXBacon = 0d;
        new Ingredientes().BACON.setValor(10.0d);
        new Ingredientes().HAMBURGUER_CARNE.setValor(10.0d);
        new Ingredientes().QUEIJO.setValor(10.0d);
        valorXBacon += new Ingredientes().BACON.getValor();
        valorXBacon += new Ingredientes().HAMBURGUER_CARNE.getValor();
        valorXBacon += new Ingredientes().QUEIJO.getValor();
        Assert.assertEquals(valorXBacon, new Cardapio().X_BACON.getValor());
    }
    
}
