package leitura;

import java.util.List;

public class GrafoDOT {
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isDirecionado() {
        return direcionado;
    }

    public void setDirecionado(boolean direcionado) {
        this.direcionado = direcionado;
    }

    public List<VerticeDOT> getVertices() {
        return vertices;
    }

    public void setVertices(List<VerticeDOT> vertices) {
        this.vertices = vertices;
    }

    public List<ArestaDOT> getArestas() {
        return arestas;
    }

    public void setArestas(List<ArestaDOT> arestas) {
        this.arestas = arestas;
    }

    private String nome;
    private boolean direcionado;
    private List<VerticeDOT> vertices;
    private List<ArestaDOT> arestas;
}
