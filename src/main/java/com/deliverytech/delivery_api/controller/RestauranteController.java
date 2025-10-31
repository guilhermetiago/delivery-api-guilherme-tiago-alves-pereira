package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.entity.Restaurante;
import com.deliverytech.delivery_api.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;


    @PostMapping
    public Restaurante cadastrar(@RequestBody Restaurante restaurante) {
        restaurante.setAtivo(true);
        return restauranteRepository.save(restaurante);
    }


    @GetMapping
    public List<Restaurante> listarTodos() {
        return restauranteRepository.findAll();
    }


    @GetMapping("/{id}")
    public Restaurante buscarPorId(@PathVariable Long id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado!"));
    }


    @PutMapping("/{id}")
    public Restaurante atualizar(@PathVariable Long id, @RequestBody Restaurante restauranteAtualizado) {
        Restaurante restaurante = buscarPorId(id);
        restaurante.setNome(restauranteAtualizado.getNome());
        restaurante.setCategoria(restauranteAtualizado.getCategoria());
        restaurante.setAvaliacao(restauranteAtualizado.getAvaliacao());
        return restauranteRepository.save(restaurante);
    }


    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        restauranteRepository.deleteById(id);
    }

    //Buscar por categoria (GET /restaurantes/categoria/{categoria})
    @GetMapping("/categoria/{categoria}")
    public List<Restaurante> buscarPorCategoria(@PathVariable String categoria) {
        return restauranteRepository.findByCategoriaContainingIgnoreCase(categoria);
    }
}
