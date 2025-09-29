package dfs;

public class DFS {

    private int tempo = 0; 

    public void buscaProfundidade(Grafo grafo) {
        for (Vertice v : grafo.getVertices()) {
            if (!v.isVisitado()) {
                visitarVertice(v);
            }
        }
    }

    private void visitarVertice(Vertice vertice) {
        tempo++;
        vertice.setTempoInicial(tempo); 
        vertice.setVisitado(true);
        System.out.println("Descoberto: " + vertice.getDesc() + " tempo: " + tempo);

        
        for (Aresta aresta : vertice.getArestas()) {
            Vertice destino = aresta.getDestino();
            if (!destino.isVisitado()) {
                visitarVertice(destino);
            }
        }

        tempo++;
        vertice.setTempoFinal(tempo);
        System.out.println("Finalizado: " + vertice.getDesc() + " tempo: " + tempo);
    }
}
