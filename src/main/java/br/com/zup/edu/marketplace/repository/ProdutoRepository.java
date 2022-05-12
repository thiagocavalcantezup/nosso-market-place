package br.com.zup.edu.marketplace.repository;

import br.com.zup.edu.marketplace.model.Produto;
import br.com.zup.edu.marketplace.model.StatusProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
    List<Produto> findAllByStatusIsAndCriadoEmLessThanEqual(StatusProduto status, LocalDateTime data);
}
