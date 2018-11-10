package com.bonifacio.lanchonete.controller;

import com.bonifacio.lanchonete.model.entity.Pedido;
import com.bonifacio.lanchonete.model.repository.PedidosRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zeehb
 */
@RestController
public class PedidosController {

    private final PedidosRepository pedidosRepository = PedidosRepository.getInstance();

    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> getPedidos() {
        return ResponseEntity.ok(this.pedidosRepository.getPedidos());
    }

    @GetMapping("/pedido/{id}")
    public ResponseEntity<Pedido> getPedidoPorId(@PathVariable Long id) {
        Pedido pedido = this.pedidosRepository.buscarPorId(id);
        if (pedido != null) {
            return ResponseEntity.ok(this.pedidosRepository.buscarPorId(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/pedido")
    public ResponseEntity<Pedido> postPedido(@RequestBody Pedido pedido) throws URISyntaxException {
        pedido = this.pedidosRepository.adicionarPedido(pedido);
        return ResponseEntity.created(new URI("/pedidos/" + pedido.getId())).body(pedido);
    }

    @PutMapping("/pedido/{id}")
    public ResponseEntity<Pedido> putPedido(@RequestBody Pedido pedido, @PathVariable Long id) throws URISyntaxException {
        pedido.setId(id);
        pedido = this.pedidosRepository.editarPedido(pedido);
        if (pedido != null) {
            return ResponseEntity.ok().body(pedido);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/pedido/{id}")
    public ResponseEntity<?> deletePedido(@PathVariable Long id) {
        boolean removido = pedidosRepository.removerPedido(id);
        if (removido) {
            return ResponseEntity.ok("Pedido removido com sucesso.");
        }
        return ResponseEntity.notFound().build();
    }
}
