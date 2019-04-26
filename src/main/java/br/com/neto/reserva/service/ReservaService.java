package br.com.neto.reserva.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neto.reserva.model.Reserva;
import br.com.neto.reserva.repository.ReservaRepository;
import br.com.neto.reserva.service.exception.ReservaInconsistenteException;


@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva salvar(Reserva reserva) {
        this.validaReservaIntervaloHorarioInconsistente(reserva);
        Reserva reservaJaCadastrada = this.reservaRepository.buscarReservaPorHorarioSalaLocal(reserva);
        if (reservaJaCadastrada == null) {
            return this.reservaRepository.save(reserva);
        }
        throw new ReservaInconsistenteException();

    }

    private void validaReservaIntervaloHorarioInconsistente(Reserva reserva) {
        Duration duration = Duration.between(reserva.getInicio(), reserva.getFim());
        if (duration.isZero() || duration.isNegative()) {
            throw new ReservaInconsistenteException();
        }
    }


}
