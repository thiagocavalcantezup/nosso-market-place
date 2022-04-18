package br.com.zup.edu.marketplace.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class NotaFiscalResponseDTO {

    private String nomeDestinatario;
    private String enderecoDestinatario;
    private String telefoneDestinatario;
    private String cpfDestinatario;
    private Set<NotaFiscalProdutoResponseDTO> itens = new HashSet<>();
    private BigDecimal valorFinal;

    public NotaFiscalResponseDTO() {}

    public NotaFiscalResponseDTO(NotaFiscal notaFiscal) {
        Usuario destinatario = notaFiscal.getDestinatario();

        this.nomeDestinatario = destinatario.getNome();
        this.enderecoDestinatario = destinatario.getEndereco();
        this.telefoneDestinatario = destinatario.getTelefone();
        this.cpfDestinatario = destinatario.getCpf();
        this.itens = notaFiscal.getItens()
                               .stream()
                               .map(NotaFiscalProdutoResponseDTO::new)
                               .collect(Collectors.toSet());
        this.valorFinal = notaFiscal.getValorFinal();
    }

    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    public String getEnderecoDestinatario() {
        return enderecoDestinatario;
    }

    public String getTelefoneDestinatario() {
        return telefoneDestinatario;
    }

    public String getCpfDestinatario() {
        return cpfDestinatario;
    }

    public Set<NotaFiscalProdutoResponseDTO> getItens() {
        return itens;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

}
