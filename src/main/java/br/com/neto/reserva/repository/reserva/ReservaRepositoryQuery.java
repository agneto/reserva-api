package br.com.neto.reserva.repository.reserva;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.neto.reserva.model.Reserva;
import br.com.neto.reserva.repository.filter.ReservaFilter;

public interface ReservaRepositoryQuery {

    public Reserva buscarReservaPorHorarioSalaLocal(Reserva reserva);
    public Page<Reserva> filtrar(ReservaFilter reservaFilter, Pageable pageable);

}
