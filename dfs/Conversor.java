package dfs;

import dfs.Grafo;
import dfs.Vertice;
import dfs.Aresta;
import leitura.GrafoDOT;
import leitura.VerticeDOT;
import leitura.ArestaDOT;

import java.util.*;

public class Conversor {

    public static Grafo converterParaDFS(GrafoDOT grafoDOT) {
        Grafo grafoDFS = new Grafo();
        Map<String, Vertice> mapaVertices = new HashMap<>();

        // Criar vértices
        for (VerticeDOT v : grafoDOT.getVertices()) {
            Vertice vertice = new Vertice(v.getNome());
            mapaVertices.put(v.getNome(), vertice);
            grafoDFS.adicionarVertice(vertice);
        }

        // Criar arestas (sem adicionar vizinhos)
        for (ArestaDOT a : grafoDOT.getArestas()) {
            Vertice origem = mapaVertices.get(a.getOrigem().getNome());
            Vertice destino = mapaVertices.get(a.getDestino().getNome());
            Aresta aresta = new Aresta(origem, destino);
            grafoDFS.adicionarAresta(aresta);
        }

        return grafoDFS;
    }
}
