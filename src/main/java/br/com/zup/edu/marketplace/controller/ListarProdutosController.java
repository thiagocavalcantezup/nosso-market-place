package br.com.zup.edu.marketplace.controller;

import br.com.zup.edu.marketplace.controller.response.ProdutoResponse;
import br.com.zup.edu.marketplace.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class ListarProdutosController {
    private final ProdutoRepository repository;

    public ListarProdutosController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/produtos")
    public ResponseEntity<?> listar() {
        List<ProdutoResponse> response = repository.findAll()
                .stream()
                .map(ProdutoResponse::new)
                .collect(Collectors.toList());

        return ok(response);
    }
}
