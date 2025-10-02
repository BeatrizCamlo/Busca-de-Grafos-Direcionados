package dfs;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        String caminhoArquivo = args[0];
        LerGrafo leitor = new LerGrafo();
        Grafo grafo = leitor.ler(caminhoArquivo);

        List<Vertice> vertices = new ArrayList<>(grafo.getVertices());
        vertices.sort(Comparator.comparing(Vertice::getDesc));

        DFS dfs = new DFS();
        dfs.buscaProfundidade(grafo);

    }
}
