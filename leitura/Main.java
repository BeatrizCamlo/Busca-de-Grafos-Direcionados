package leitura;

import bfs.BFS;
import dfs.Conversor;
import dfs.DFS;
import dfs.Grafo;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Verificar se foi fornecido o caminho do arquivo
        if (args.length == 0) {
            System.err.println("ERRO: Caminho do arquivo não fornecido!");
            return;
        }

        String caminhoArquivo = args[0];

        // Validar extensão do arquivo
        if (!caminhoArquivo.endsWith(".dot") && !caminhoArquivo.endsWith(".gv")) {
            System.err.println("ERRO: Extensão de arquivo inválida!");
            System.err.println("Apenas arquivos .dot e .gv são aceitos.");
            System.err.println("Arquivo fornecido: " + caminhoArquivo);
            return;
        }

        try {
            // --- Ler e parsear grafo ---
            ParserDOT parser = new ParserDOT();
            System.out.println("Lendo arquivo: " + caminhoArquivo);

            GrafoDOT grafo = parser.parsearArquivo(caminhoArquivo);
            parser.imprimirGrafo(grafo);

            if (parser.temErros()) {
                System.err.println("Parsing concluído com erros! Não é possível executar BFS/DFS.");
                return;
            }

            // --- BFS ---
            Set<String> verticesBFS = new HashSet<>();
            List<String[]> arestasBFS = new ArrayList<>();

            for (var v : grafo.getVertices()) {
                verticesBFS.add(v.getNome());
            }

            for (var a : grafo.getArestas()) {
                arestasBFS.add(new String[]{a.getOrigem().getNome(), a.getDestino().getNome()});
            }

            BFS bfs = new BFS(verticesBFS, arestasBFS, grafo.isDirecionado());
            System.out.println("\n--- BFS ---");
            System.out.println("Matriz de Adjacência:");
            bfs.printMatriz();

            List<String> resultadoBFS = bfs.bfs();
            System.out.println("Percurso BFS: " + String.join(" -> ", resultadoBFS));

            // --- DFS ---
            System.out.println("\n--- DFS ---");
            Grafo grafoDFS = Conversor.converterParaDFS(grafo);
            DFS dfs = new DFS();
            dfs.buscaProfundidade(grafoDFS);

        } catch (IOException e) {
            System.err.println("ERRO ao ler arquivo: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("ERRO inesperado: " + e.getMessage());
        }
    }
}
