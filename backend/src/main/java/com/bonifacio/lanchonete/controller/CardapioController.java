package com.bonifacio.lanchonete.controller;
;
import com.bonifacio.lanchonete.model.entity.Cardapio;
import com.bonifacio.lanchonete.model.entity.Lanche;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zeehb
 */

@RestController
public class CardapioController {

    @GetMapping("/cardapio")
    public ResponseEntity<List<Lanche>> getCardapio() {
        Cardapio cardapio = new Cardapio();
        return ResponseEntity.ok(cardapio.getLanches());
    }
}
