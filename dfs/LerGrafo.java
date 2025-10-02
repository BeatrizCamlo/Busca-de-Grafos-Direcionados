package dfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LerGrafo {

    public Grafo ler(String caminho) throws IOException {
        Grafo grafo = new Grafo();
        Map<String, Vertice> mapaVertices = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.contains("->")) {
                    String[] partes = linha.split("->");
                    String origemNome = partes[0].trim();
                    String destinoNome = partes[1].replace(";", "").trim();

                    Vertice origem = mapaVertices.computeIfAbsent(origemNome, Vertice::new);
                    Vertice destino = mapaVertices.computeIfAbsent(destinoNome, Vertice::new);

                    Aresta aresta = new Aresta(origem, destino);
                    grafo.adicionarAresta(aresta);

                    // garante que os vértices estão dentro do grafo
                    grafo.adicionarVertice(origem);
                    grafo.adicionarVertice(destino);
                }
            }
        }
        return grafo;
    }
}
