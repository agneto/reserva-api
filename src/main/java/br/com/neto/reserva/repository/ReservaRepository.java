package br.com.neto.reserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neto.reserva.model.Reserva;

public interface ReservaRepository  extends JpaRepository<Reserva, Long>{

}
