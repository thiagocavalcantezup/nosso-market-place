package br.com.zup.edu.marketplace.controller;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.edu.marketplace.model.Produto;
import br.com.zup.edu.marketplace.model.Usuario;
import br.com.zup.edu.marketplace.repository.ProdutoRepository;
import br.com.zup.edu.marketplace.repository.UsuarioRepository;

@RestController
@RequestMapping(UsuarioController.BASE_URI)
public class UsuarioController {

    public final static String BASE_URI = "/usuarios";

    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;

    public UsuarioController(UsuarioRepository usuarioRepository,
                             ProdutoRepository produtoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    @PostMapping("/{usuarioId}" + ProdutoController.BASE_URI + "/{produtoId}")
    public ResponseEntity<Void> addToWishlist(@PathVariable Long usuarioId,
                                              @PathVariable Long produtoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                                           .orElseThrow(
                                               () -> new ResponseStatusException(
                                                   HttpStatus.NOT_FOUND,
                                                   "Não existe um usuário com o id fornecido."
                                               )
                                           );

        Produto produto = produtoRepository.findById(produtoId)
                                           .orElseThrow(
                                               () -> new ResponseStatusException(
                                                   HttpStatus.NOT_FOUND,
                                                   "Não existe um produto com o id fornecido."
                                               )
                                           );

        usuario.adicionar(produto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    @DeleteMapping("/{usuarioId}" + ProdutoController.BASE_URI + "/{produtoId}")
    public ResponseEntity<Void> removeFromWishlist(@PathVariable Long usuarioId,
                                                   @PathVariable Long produtoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                                           .orElseThrow(
                                               () -> new ResponseStatusException(
                                                   HttpStatus.NOT_FOUND,
                                                   "Não existe um usuário com o id fornecido."
                                               )
                                           );

        Produto produto = produtoRepository.findById(produtoId)
                                           .orElseThrow(
                                               () -> new ResponseStatusException(
                                                   HttpStatus.NOT_FOUND,
                                                   "Não existe um produto com o id fornecido."
                                               )
                                           );

        usuario.remover(produto);

        return ResponseEntity.noContent().build();
    }

}
