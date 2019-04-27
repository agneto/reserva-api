package br.com.neto.reserva.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ReservaFilter {

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate dataInicial;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate dataFinal;

    public LocalDate getDataInicial() {
        return this.dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return this.dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }
}
