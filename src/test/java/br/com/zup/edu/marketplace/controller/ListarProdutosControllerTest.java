package br.com.zup.edu.marketplace.controller;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import br.com.zup.edu.marketplace.model.Produto;
import br.com.zup.edu.marketplace.repository.ProdutoRepository;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@ActiveProfiles("test")
public class ListarProdutosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProdutoRepository produtoRepository;

    @BeforeEach
    void setUp() {
        produtoRepository.deleteAll();
    }

    @Test
    void deveListarTodosOsProdutos() throws Exception {
        // cenário (given)
        //
        Produto produto1 = new Produto("Café Melitta", "Café em pó.", new BigDecimal("9.00"));
        Produto produto2 = new Produto("Café Pilão", "Café em pó.", new BigDecimal("8.50"));
        produtoRepository.saveAll(List.of(produto1, produto2));

        MockHttpServletRequestBuilder request = get("/produtos").contentType(APPLICATION_JSON);

        // ação (when) e corretude (then)
        //
        String response = mockMvc.perform(request)
                                 .andExpect(status().isOk())
                                 .andReturn()
                                 .getResponse()
                                 .getContentAsString(UTF_8);

        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<Produto> produtos = objectMapper.readValue(
            response, typeFactory.constructCollectionType(List.class, Produto.class)
        );

        assertThat(produtos).hasSize(2)
                            .extracting("titulo", "descricao", "preco")
                            .contains(
                                new Tuple(
                                    produto1.getTitulo(), produto1.getDescricao(),
                                    produto1.getPreco()
                                ),
                                new Tuple(
                                    produto2.getTitulo(), produto2.getDescricao(),
                                    produto2.getPreco()
                                )
                            );
    }

    @Test
    void deveRetornarUmaColecaoVaziaCasoNaoExistamProdutosCadastrados() throws Exception {
        // cenário (given)
        //
        MockHttpServletRequestBuilder request = get("/produtos").contentType(APPLICATION_JSON);

        // ação (when) e corretude (then)
        //
        String response = mockMvc.perform(request)
                                 .andExpect(status().isOk())
                                 .andReturn()
                                 .getResponse()
                                 .getContentAsString(UTF_8);

        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<Produto> produtos = objectMapper.readValue(
            response, typeFactory.constructCollectionType(List.class, Produto.class)
        );

        assertThat(produtos).isEmpty();
    }

}
