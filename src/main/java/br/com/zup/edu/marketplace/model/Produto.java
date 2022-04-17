package br.com.zup.edu.marketplace.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusProduto status = StatusProduto.PENDENTE;

    @Column(nullable = false)
    private BigDecimal preco;

    public Produto(String titulo, String descricao, BigDecimal preco) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
    }

    /**
     * @deprecated construtor para uso exclusivo do Hibernate
     */
    @Deprecated
    public Produto() {}

    public void atualizar(String descricao) {
        if (!isPendente()) {
            throw new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Impossível atualizar o produto. Ele não está com o status pendente."
            );
        }

        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public boolean isInativo() {
        return status.equals(StatusProduto.INATIVO);
    }

    public boolean isPendente() {
        return status.equals(StatusProduto.PENDENTE);
    }

    public void setStatus(StatusProduto status) {
        this.status = status;
    }

}
