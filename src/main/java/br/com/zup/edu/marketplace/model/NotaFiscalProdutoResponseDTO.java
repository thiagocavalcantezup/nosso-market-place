package br.com.zup.edu.marketplace.model;

import java.math.BigDecimal;

public class NotaFiscalProdutoResponseDTO {

    private String titulo;
    private String descricao;
    private String status;
    private BigDecimal preco;

    public NotaFiscalProdutoResponseDTO() {}

    public NotaFiscalProdutoResponseDTO(Produto produto) {
        this.titulo = produto.getTitulo();
        this.descricao = produto.getDescricao();
        this.status = produto.getStatus().name();
        this.preco = produto.getPreco();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getPreco() {
        return preco;
    }

}
