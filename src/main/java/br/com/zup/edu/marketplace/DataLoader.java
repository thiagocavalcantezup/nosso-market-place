package br.com.zup.edu.marketplace;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.zup.edu.marketplace.model.Produto;
import br.com.zup.edu.marketplace.model.StatusProduto;
import br.com.zup.edu.marketplace.repository.ProdutoRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;

    public DataLoader(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Produto produtoPendente = new Produto(
            "Refrigerante", "Lata de refrigerante", new BigDecimal("5.00")
        );
        Produto produtoCadastrado = new Produto("Suco", "Garrafa de suco", new BigDecimal("6.00"));
        produtoCadastrado.setStatus(StatusProduto.CADASTRADO);
        Produto produtoInativo = new Produto("Água", "Garrafa de água", new BigDecimal("3.00"));
        produtoInativo.setStatus(StatusProduto.INATIVO);

        produtoRepository.save(produtoPendente);
        produtoRepository.save(produtoCadastrado);
        produtoRepository.save(produtoInativo);
    }

}
