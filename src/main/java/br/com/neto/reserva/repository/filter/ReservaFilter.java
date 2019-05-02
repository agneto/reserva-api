package br.com.neto.reserva.repository.filter;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ReservaFilter {

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataInicial;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataFinal;

    public LocalDateTime getDataInicial() {
        return this.dataInicial;
    }

    public void setDataInicial(LocalDateTime dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDateTime getDataFinal() {
        return this.dataFinal;
    }

    public void setDataFinal(LocalDateTime dataFinal) {
        this.dataFinal = dataFinal;
    }
}
