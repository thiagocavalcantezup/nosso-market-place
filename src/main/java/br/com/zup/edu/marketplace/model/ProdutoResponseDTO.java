package br.com.zup.edu.marketplace.model;

import java.math.BigDecimal;

public class ProdutoResponseDTO {

    private String titulo;
    private String descricao;
    private BigDecimal preco;

    public ProdutoResponseDTO() {}

    public ProdutoResponseDTO(Produto produto) {
        this.titulo = produto.getTitulo();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

}
