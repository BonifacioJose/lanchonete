package com.bonifacio.lanchonete.model.repository;

import com.bonifacio.lanchonete.model.entity.Pedido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zeehb
 */
public class PedidosRepository {

    private static final PedidosRepository INSTANCE = new PedidosRepository();

    private PedidosRepository() {
    }

    private final List<Pedido> pedidos = new ArrayList<>();
    private long idSequencial = 1;

    public Pedido adicionarPedido(Pedido pedido) {
        pedido.setId(idSequencial);
        pedidos.add(pedido);
        idSequencial++;
        return pedido;
    }

    public Pedido editarPedido(Pedido pedido) {
        Pedido pedidoSalvo = buscarPorId(pedido.getId());
        if (pedidoSalvo != null) {
            pedidoSalvo.setLanche(pedido.getLanche());
            return pedidoSalvo;
        }
        return null;
    }

    public Pedido buscarPorId(Long id) {
        Pedido pedido = new Pedido();
        pedido.setId(id);
        int index = pedidos.indexOf(pedido);
        if (index > -1) {
            return pedidos.get(index);
        }
        return null;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public boolean removerPedido(Long id) {
        Pedido pedido = buscarPorId(id);
        if (pedido != null) {
            return pedidos.remove(pedido);
        }
        return false;
    }

    public static PedidosRepository getInstance() {
        return INSTANCE;
    }

}
