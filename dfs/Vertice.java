package dfs;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private List<Aresta> arestas;
    private String desc;
    private int tempoInicial;
    private int tempoFinal;
    private boolean visitado = false;

    public Vertice(String desc) {
        this.desc = desc;
        this.arestas = new ArrayList<>();
    }

    public void adicionarAresta(Aresta aresta) {
        arestas.add(aresta);
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public List<Vertice> getVizinhos() {
        List<Vertice> vizinhos = new ArrayList<>();
        for (Aresta a : arestas) {
            vizinhos.add(a.getDestino());
        }
        return vizinhos;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getTempoInicial() {
        return tempoInicial;
    }

    public void setTempoInicial(int tempoInicial) {
        this.tempoInicial = tempoInicial;
    }

    public int getTempoFinal() {
        return tempoFinal;
    }

    public void setTempoFinal(int tempoFinal) {
        this.tempoFinal = tempoFinal;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    @Override
    public String toString() {
        return desc;
    }
}
