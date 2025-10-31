package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.entity.Pedido;
import com.deliverytech.delivery_api.repository.ClienteRepository;
import com.deliverytech.delivery_api.repository.PedidoRepository;
import com.deliverytech.delivery_api.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;


    @PostMapping
    public Pedido criar(@RequestBody Pedido pedido) {
        pedido.setStatus("NOVO");
        return pedidoRepository.save(pedido);
    }


    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }


    @GetMapping("/cliente/{idCliente}")
    public List<Pedido> buscarPorCliente(@PathVariable Long idCliente) {
        return pedidoRepository.findByClienteId(idCliente);
    }


    @PutMapping("/{id}/status")
    public Pedido atualizarStatus(@PathVariable Long id, @RequestBody String novoStatus) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));
        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }
}
