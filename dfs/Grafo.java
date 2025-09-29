package dfs;
import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private List<Vertice> vertices;
    private List<Aresta> arestas;

    public Grafo() {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }

    public void adicionarVertice(Vertice v) {
        vertices.add(v);
    }

    public void adicionarAresta(Aresta a) {
        arestas.add(a);
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

   
    public List<Vertice> getAdjacentes(Vertice v) {
        List<Vertice> adj = new ArrayList<>();
        for (Aresta a : arestas) {
            if (a.getOrigem().equals(v)) {
                adj.add(a.getDestino());
            }
        }
        return adj;
    }
}
