package com.bonifacio.lanchonete.controller;

import com.bonifacio.lanchonete.model.entity.Lanche;
import com.bonifacio.lanchonete.model.entity.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zeehb
 */
@RestController
public class PromocaoController {
    
    @PostMapping("/promocao")
    public ResponseEntity<Double> postPrecoFinalParaLanche(@RequestBody Lanche lanche) {
        Pedido pedido = new Pedido();
        pedido.setLanche(lanche);
        return ResponseEntity.ok(lanche.getValor());
    }
}
