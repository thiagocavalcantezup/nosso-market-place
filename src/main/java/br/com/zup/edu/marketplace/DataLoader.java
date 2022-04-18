package br.com.zup.edu.marketplace;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.zup.edu.marketplace.model.NotaFiscal;
import br.com.zup.edu.marketplace.model.Produto;
import br.com.zup.edu.marketplace.model.StatusProduto;
import br.com.zup.edu.marketplace.model.Usuario;
import br.com.zup.edu.marketplace.repository.NotaFiscalRepository;
import br.com.zup.edu.marketplace.repository.ProdutoRepository;
import br.com.zup.edu.marketplace.repository.UsuarioRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;
    private final NotaFiscalRepository notaFiscalRepository;
    private final UsuarioRepository usuarioRepository;

    public DataLoader(ProdutoRepository produtoRepository,
                      NotaFiscalRepository notaFiscalRepository,
                      UsuarioRepository usuarioRepository) {
        this.produtoRepository = produtoRepository;
        this.notaFiscalRepository = notaFiscalRepository;
        this.usuarioRepository = usuarioRepository;
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

        Usuario usuario = new Usuario(
            "Thiago", "446.107.690-34", "Rua X, Cidade Y", "+55(81)99999-4444"
        );
        usuarioRepository.save(usuario);

        NotaFiscal notaFiscal = new NotaFiscal(
            usuario, List.of(produtoPendente, produtoCadastrado, produtoInativo)
        );
        notaFiscalRepository.save(notaFiscal);
    }

}
