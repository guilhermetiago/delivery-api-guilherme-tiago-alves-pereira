package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.entity.Produto;
import com.deliverytech.delivery_api.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface ProdutoRepository extends JpaRepository <Produto, Long>{

    List<Produto> findByRestauranteId(long restauranteId);

    List<Produto> findByDisponivelTrue();


}
