package br.com.zup.edu.marketplace.model;

import javax.validation.constraints.NotBlank;

public class ProdutoPatchDTO {

    @NotBlank
    private String descricao;

    public ProdutoPatchDTO() {}

    public ProdutoPatchDTO(@NotBlank String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
