package br.com.neto.reserva.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.neto.reserva.model.Reserva;
import br.com.neto.reserva.repository.ReservaRepository;
import br.com.neto.reserva.repository.filter.ReservaFilter;
import br.com.neto.reserva.service.ReservaService;

@RestController
@RequestMapping("/reservas/v1")
public class ReservaResource {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> criar(@Valid @RequestBody Reserva reserva, HttpServletResponse response) {
        Reserva reservaSalvo = this.reservaService.salvar(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaSalvo);
    }

    @GetMapping("listarTodos")
    public List<Reserva> listar() {
        return this.reservaRepository.findAll();
    }

    @GetMapping(params = "listarPaginada")
    public Page<Reserva> listarPaginada(ReservaFilter reservaFilter, Pageable pageable) {
        return this.reservaRepository.filtrar(reservaFilter, pageable);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        this.reservaRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Reserva> atualizar(@PathVariable Long codigo, @Valid @RequestBody Reserva reserva) {
        try {
            Reserva reservaSalvo = this.reservaService.atualizar(codigo, reserva);
            return ResponseEntity.ok(reservaSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
