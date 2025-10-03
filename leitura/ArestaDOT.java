package leitura;

public class ArestaDOT {
    public VerticeDOT getOrigem() {
        return origem;
    }

    public void setOrigem(VerticeDOT origem) {
        this.origem = origem;
    }

    public VerticeDOT getDestino() {
        return destino;
    }

    public void setDestino(VerticeDOT destino) {
        this.destino = destino;
    }

    public boolean isDirecionada() {
        return direcionada;
    }

    public void setDirecionada(boolean direcionada) {
        this.direcionada = direcionada;
    }

    private VerticeDOT origem;
    private VerticeDOT destino;
    private boolean direcionada;
}
