package ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception;

public class LimiteCompradores extends RuntimeException {
    public LimiteCompradores() {
        super("Se alcanzó el límite de compradores para este artículo.");
    }
}
