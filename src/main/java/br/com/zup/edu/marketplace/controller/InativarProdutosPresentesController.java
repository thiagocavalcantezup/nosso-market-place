package br.com.zup.edu.marketplace.controller;

import br.com.zup.edu.marketplace.model.Produto;
import br.com.zup.edu.marketplace.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.zup.edu.marketplace.model.StatusProduto.PENDENTE;

@RestController
public class InativarProdutosPresentesController {
    private final ProdutoRepository repository;


    public InativarProdutosPresentesController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/produtos/inativar")
    @Transactional
    public ResponseEntity<?> inativar() {

        List<Produto> pendentes = repository.findAllByStatusIsAndCriadoEmLessThanEqual(PENDENTE, LocalDateTime.now());

        List<Long> ids = pendentes.stream()
                .peek(Produto::inativar)
                .map(Produto::getId)
                .collect(Collectors.toList());


        return ResponseEntity.ok(ids);
    }
}
