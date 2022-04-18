package br.com.zup.edu.marketplace.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.zup.edu.marketplace.model.NotaFiscal;

public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {

    @Query("SELECT nf FROM NotaFiscal nf JOIN FETCH nf.destinatario JOIN FETCH nf.itens WHERE nf.id = :id")
    Optional<NotaFiscal> findWithItensById(Long id);

}
