package br.com.zup.edu.marketplace.model;

public class NotaFiscalProdutoResponseDTO {

    private String titulo;
    private String descricao;
    private String status;
    private String preco;

    public NotaFiscalProdutoResponseDTO() {}

    public NotaFiscalProdutoResponseDTO(Produto produto) {
        this.titulo = produto.getTitulo();
        this.descricao = produto.getDescricao();
        this.status = produto.getStatus().name();
        this.preco = produto.getPreco().toString();
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

    public String getPreco() {
        return preco;
    }

}
