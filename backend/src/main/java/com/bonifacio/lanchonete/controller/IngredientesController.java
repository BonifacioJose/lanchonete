package com.bonifacio.lanchonete.controller;

import com.bonifacio.lanchonete.model.entity.Cardapio;
import com.bonifacio.lanchonete.model.entity.Ingrediente;
import com.bonifacio.lanchonete.model.entity.Ingredientes;
import com.bonifacio.lanchonete.model.entity.Lanche;
import com.bonifacio.lanchonete.model.entity.PorcaoIngrediente;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zeehb
 */
@RestController
public class IngredientesController {

    @GetMapping("/ingredientes")
    public ResponseEntity<List<Ingrediente>> getIngredientes() {
        List<Ingrediente> ingredientes = new ArrayList<>();
        ingredientes.add(new Ingredientes().ALFACE);
        ingredientes.add(new Ingredientes().BACON);
        ingredientes.add(new Ingredientes().HAMBURGUER_CARNE);
        ingredientes.add(new Ingredientes().OVO);
        ingredientes.add(new Ingredientes().QUEIJO);
        return ResponseEntity.ok(ingredientes);
    }

    @GetMapping("/ingredientes/{lanche}")
    public ResponseEntity<List<PorcaoIngrediente>> getIngredientesPorLanche(@PathVariable String lanche) {
        for (Lanche iLanche : new Cardapio().getLanches()) {
            if (iLanche.getNome().equals(lanche)) {
                return ResponseEntity.ok(iLanche.getPorcoesIngredientes());
            }
        }
        return ResponseEntity.notFound().build();
    }
}
