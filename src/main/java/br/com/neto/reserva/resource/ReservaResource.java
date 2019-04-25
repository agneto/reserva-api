package br.com.neto.reserva.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neto.reserva.model.Reserva;
import br.com.neto.reserva.repository.ReservaRepository;


@RestController
@RequestMapping("/reservas/v1")
public class ReservaResource {

    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping("listarTodos")
    public List<Reserva> listar() {
        return this.reservaRepository.findAll();
    }
}
