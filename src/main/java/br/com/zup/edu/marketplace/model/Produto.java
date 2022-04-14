package br.com.zup.edu.marketplace.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    public Long getId() {
        return id;
    }

}
