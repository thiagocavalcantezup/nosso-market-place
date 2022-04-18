package br.com.zup.edu.marketplace.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.edu.marketplace.model.NotaFiscal;
import br.com.zup.edu.marketplace.model.NotaFiscalResponseDTO;
import br.com.zup.edu.marketplace.repository.NotaFiscalRepository;

@RestController
@RequestMapping(NotaFiscalController.BASE_URI)
public class NotaFiscalController {

    public final static String BASE_URI = "/notas-fiscais";

    private final NotaFiscalRepository notaFiscalRepository;

    public NotaFiscalController(NotaFiscalRepository notaFiscalRepository) {
        this.notaFiscalRepository = notaFiscalRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaFiscalResponseDTO> show(@PathVariable Long id) {
        NotaFiscal notaFiscal = notaFiscalRepository.findById(id)
                                                    .orElseThrow(
                                                        () -> new ResponseStatusException(
                                                            HttpStatus.NOT_FOUND,
                                                            "Não existe uma nota fiscal com o id informado."
                                                        )
                                                    );

        return ResponseEntity.ok(new NotaFiscalResponseDTO(notaFiscal));
    }

}
