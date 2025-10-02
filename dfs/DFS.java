package dfs;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DFS {

    private int tempo = 0;

    public void buscaProfundidade(Grafo grafo) {
        List<Vertice> vertices = grafo.getVertices();
        Collections.sort(vertices, Comparator.comparing(Vertice::getDesc));

        for (Vertice v : vertices) {
            if (!v.isVisitado()) {
                visitarVertice(v);
            }
        }

        System.out.println("\nTempos de descoberta/finalizacao:");
        for (Vertice v : vertices) {
            System.out.println(v.getDesc() +
                " -> inicio = " + v.getTempoInicial() +
                ", fim = " + v.getTempoFinal());
        }
    }

    private void visitarVertice(Vertice vertice) {
        tempo++;
        vertice.setTempoInicial(tempo);
        vertice.setVisitado(true);
        System.out.println("Visitando: " + vertice.getDesc());

        List<Vertice> vizinhos = vertice.getVizinhos();
        Collections.sort(vizinhos, Comparator.comparing(Vertice::getDesc));

        for (Vertice vizinho : vizinhos) {
            if (!vizinho.isVisitado()) {
                visitarVertice(vizinho);
            }
        }

        tempo++;
        vertice.setTempoFinal(tempo);
    }
}
