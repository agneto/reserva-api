package br.com.neto.reserva.service.exception;

public class ReservaInconsistenteException extends RuntimeException {

    public ReservaInconsistenteException(String msg) {
        super(msg);
    }

    private static final long serialVersionUID = 1L;
}
