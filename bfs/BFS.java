package bfs;

import java.util.*;

public class BFS {
    private int[][] matriz;
    private List<String> vertices;
    private Map<String, Integer> verticeIndice;


    public BFS(Set<String> vertices, List<String[]> arestas, boolean isDirected) {
        this.vertices = new ArrayList<>(vertices);
        Collections.sort(this.vertices);

        this.verticeIndice = new HashMap<>();
        for (int i = 0; i < this.vertices.size(); i++) {
            this.verticeIndice.put(this.vertices.get(i), i);
        }

        int n = this.vertices.size();
        this.matriz = new int[n][n];

        for (String[] aresta : arestas) {
            String origem = aresta[0];
            String destino = aresta[1];

            int indiceOrigem = this.verticeIndice.get(origem);
            int indiceDestino = this.verticeIndice.get(destino);

            this.matriz[indiceOrigem][indiceDestino] = 1;

            if (!isDirected) {
                this.matriz[indiceDestino][indiceOrigem] = 1;
            }
        }
    }

    public List<String> bfs() {
        Queue<Integer> fila = new LinkedList<>();
        boolean[] visitado = new boolean[this.matriz.length];
        List<String> resultado = new ArrayList<>();

        int primeiroVertice = 0;

        fila.add(0);
        visitado[primeiroVertice] = true;
        resultado.add(this.vertices.get(primeiroVertice));

        while (!fila.isEmpty()) {
            int u = fila.poll();

            for (int v = 0; v < this.matriz.length; v++) {
                if (this.matriz[u][v] == 1 && !visitado[v]) {
                    visitado[v] = true;
                    fila.add(v);
                    resultado.add(this.vertices.get(v));
                }
            }
        }
        return resultado;
    }

    public void printMatriz() {
        System.out.print("      ");
        for (String vertex : vertices) {
            System.out.print(vertex + " ");
        }
        System.out.println("\n---------------------");

        for (int i = 0; i < matriz.length; i++) {
            System.out.print(vertices.get(i) + " |   ");
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

}

