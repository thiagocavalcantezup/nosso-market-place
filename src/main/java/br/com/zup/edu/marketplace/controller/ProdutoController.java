package br.com.zup.edu.marketplace.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.edu.marketplace.model.Produto;
import br.com.zup.edu.marketplace.model.ProdutoPatchDTO;
import br.com.zup.edu.marketplace.repository.ProdutoRepository;

@RestController
@RequestMapping(ProdutoController.BASE_URI)
public class ProdutoController {

    public final static String BASE_URI = "/produtos";

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id)
                                           .orElseThrow(
                                               () -> new ResponseStatusException(
                                                   HttpStatus.NOT_FOUND,
                                                   "Não existe um produto com o id informado."
                                               )
                                           );

        if (produto.isInativo()) {
            produtoRepository.delete(produto);
            return ResponseEntity.noContent().build();
        }

        throw new ResponseStatusException(
            HttpStatus.UNPROCESSABLE_ENTITY, "Impossível remover um produto que não está inativo."
        );
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<Void> patch(@PathVariable Long id,
                                      @RequestBody @Valid ProdutoPatchDTO produtoPatchDTO) {
        Produto produto = produtoRepository.findById(id)
                                           .orElseThrow(
                                               () -> new ResponseStatusException(
                                                   HttpStatus.NOT_FOUND,
                                                   "Não existe um produto com o id informado."
                                               )
                                           );

        produto.atualizar(produtoPatchDTO.getDescricao());
        produtoRepository.save(produto);

        return ResponseEntity.noContent().build();
    }

}
