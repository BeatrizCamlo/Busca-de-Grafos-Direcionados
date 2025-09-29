package dfs;

public class Main {
    public static void main(String[] args) {
       
        Vertice vA = new Vertice("A");
        Vertice vB = new Vertice("B");
        Vertice vC = new Vertice("C");
        Vertice vD = new Vertice("D");
        Vertice vE = new Vertice("E");
        Vertice vF = new Vertice("F");
        Vertice vG = new Vertice("G");
        Vertice vH = new Vertice("H");
        
        Grafo grafo = new Grafo();
        grafo.adicionarVertice(vA);
        grafo.adicionarVertice(vB);
        grafo.adicionarVertice(vC);
        grafo.adicionarVertice(vD);
        grafo.adicionarVertice(vE);
        grafo.adicionarVertice(vF);
        grafo.adicionarVertice(vG);
        grafo.adicionarVertice(vH);
       
        Aresta aAB = new Aresta(vA, vB);
        Aresta aAH = new Aresta(vA, vH);
        Aresta aAC = new Aresta(vA, vC);
        Aresta aBD = new Aresta(vB, vD);
        Aresta aBF = new Aresta(vB, vF);
        Aresta aFC = new Aresta(vF, vC);
        Aresta aCE = new Aresta(vC, vE);
        Aresta aCG = new Aresta(vC, vG);
        Aresta aDE = new Aresta(vD, vE);
        Aresta aEF = new Aresta(vE, vF);
        Aresta aFG = new Aresta(vF, vG);
        Aresta aGH = new Aresta(vG, vH);
        Aresta aHF = new Aresta(vH, vF);
        Aresta aGA = new Aresta(vG, vA);

        grafo.adicionarAresta(aAB);
        grafo.adicionarAresta(aAH);
        grafo.adicionarAresta(aAC);
        grafo.adicionarAresta(aBD);
        grafo.adicionarAresta(aBF);
        grafo.adicionarAresta(aFC);
        grafo.adicionarAresta(aCE);
        grafo.adicionarAresta(aCG);
        grafo.adicionarAresta(aDE);
        grafo.adicionarAresta(aEF);
        grafo.adicionarAresta(aFG);
        grafo.adicionarAresta(aGH);
        grafo.adicionarAresta(aHF);
        grafo.adicionarAresta(aGA);
       
        vA.adicionarAresta(aAB);
        vA.adicionarAresta(aAC);
        vB.adicionarAresta(aBD);
        vC.adicionarAresta(aCE);
        vD.adicionarAresta(aDE);
        vE.adicionarAresta(aEF);
        vF.adicionarAresta(aFG);
        vG.adicionarAresta(aGH);

        
        DFS dfs = new DFS();
        dfs.buscaProfundidade(grafo);
    }
}
