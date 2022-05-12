package br.com.zup.edu.marketplace.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static br.com.zup.edu.marketplace.model.StatusProduto.*;

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
    private StatusProduto status;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;

    @Column(nullable = false)
    private BigDecimal preco;

    public Produto(String titulo, String descricao, BigDecimal preco) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.status = PENDENTE;
        this.criadoEm = LocalDateTime.now();
    }

    public Produto(String titulo, String descricao, BigDecimal preco, StatusProduto status, LocalDateTime criadoEm) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.status = status;
        this.criadoEm=criadoEm;
    }

    /**
     * @deprecated construtor para uso exclusivo do Hibernate
     */
    @Deprecated
    public Produto() {
    }

    public Long getId() {
        return id;
    }

    public StatusProduto getStatus() {
        return status;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void inativar() {
        this.status = INATIVO;
        this.atualizadoEm = LocalDateTime.now();
    }


}
