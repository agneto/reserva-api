package br.com.neto.reserva.service;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
        throw new ReservaInconsistenteException("Você está tentando inserir uma reserva já agendada!");
    }

    public Reserva atualizar(Long codigo,Reserva reserva) {
        Reserva reservaSalvo = this.buscarReservaExistente(codigo);
        this.validaReservaIntervaloHorarioInconsistente(reservaSalvo);

        BeanUtils.copyProperties(reserva, reservaSalvo, "codigo");
        return this.reservaRepository.save(reservaSalvo);
    }

    private void validaReservaIntervaloHorarioInconsistente(Reserva reserva) {
        Duration duration = Duration.between(reserva.getInicio(), reserva.getFim());
        if (duration.isZero() || duration.isNegative()) {
            throw new ReservaInconsistenteException("A data inicial tem de ser menor que a data final");
        }
    }

    private Reserva buscarReservaExistente(Long codigo) {
        Optional<Reserva> lancamentoSalvo = this.reservaRepository.findById(codigo);
        if (!lancamentoSalvo.isPresent()) {
            throw new IllegalArgumentException();
        }
        return lancamentoSalvo.get();
    }


}
