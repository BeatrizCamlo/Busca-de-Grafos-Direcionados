package leitura;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ParserDOT {
    
    private boolean temErros = false;
    
    public GrafoDOT parsearArquivo(String caminhoArquivo) throws IOException {
        GrafoDOT grafo = new GrafoDOT();
        Map<String, VerticeDOT> mapaVertices = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean dentroDoGrafo = false;
            
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                
                // Pular linhas vazias e comentários
                if (linha.isEmpty() || linha.startsWith("//") || linha.startsWith("#")) {
                    continue;
                }
                
                // Detectar início do grafo
                if (linha.startsWith("digraph") || linha.startsWith("graph")) {
                    dentroDoGrafo = true;
                    
                    // Extrair nome do grafo (opcional)
                    String[] partes = linha.split("\\s+");
                    if (partes.length >= 2 && !partes[1].equals("{")) {
                        grafo.setNome(partes[1].replace("{", "").trim());
                    } else {
                        grafo.setNome("grafo_" + (linha.startsWith("digraph") ? "direcionado" : "nao_direcionado"));
                    }
                    
                    // Determinar se é direcionado
                    grafo.setDirecionado(linha.startsWith("digraph"));
                    
                    // Inicializar listas
                    grafo.setVertices(new ArrayList<>());
                    grafo.setArestas(new ArrayList<>());
                    
                    continue;
                }
                
                // Fim do grafo
                if (linha.equals("}")) {
                    dentroDoGrafo = false;
                    continue;
                }
                
                // Processar arestas apenas se estivermos dentro do grafo
                if (dentroDoGrafo && (linha.contains("->") || linha.contains("--"))) {
                    processarAresta(linha, grafo, mapaVertices);
                }
            }
        }
        
        return grafo;
    }
    
    private void processarAresta(String linha, GrafoDOT grafo, Map<String, VerticeDOT> mapaVertices) {
        // Remover ponto e vírgula do final (opcional)
        if (linha.endsWith(";")) {
            linha = linha.substring(0, linha.length() - 1);
        }
        linha = linha.trim();
        
        // Determinar tipo de aresta baseado no tipo do grafo
        boolean isDirecionada = grafo.isDirecionado();
        String separador = isDirecionada ? "->" : "--";
        
        // Verificar se a linha usa o separador correto
        if (isDirecionada && linha.contains("--")) {
            System.err.println("ERRO: Aresta não direcionada (--) encontrada em grafo direcionado: " + linha);
            System.err.println("Use '->' para arestas direcionadas em grafos 'digraph'");
            temErros = true;
            return;
        } else if (!isDirecionada && linha.contains("->")) {
            System.err.println("ERRO: Aresta direcionada (->) encontrada em grafo não direcionado: " + linha);
            System.err.println("Use '--' para arestas não direcionadas em grafos 'graph'");
            temErros = true;
            return;
        }
        
        // Dividir a linha em vértices usando o separador correto
        String[] vertices = linha.split("\\s*" + separador.replace("->", "\\->").replace("--", "\\-\\-") + "\\s*");
        
        // Processar arestas sequenciais: a->b->c cria a->b e b->c
        for (int i = 0; i < vertices.length - 1; i++) {
            String origemNome = vertices[i].trim();
            String destinoNome = vertices[i + 1].trim();
            
            // Criar ou obter vértices
            VerticeDOT origem = obterOuCriarVertice(origemNome, mapaVertices, grafo);
            VerticeDOT destino = obterOuCriarVertice(destinoNome, mapaVertices, grafo);
            
            // Criar aresta
            ArestaDOT aresta = new ArestaDOT();
            aresta.setOrigem(origem);
            aresta.setDestino(destino);
            aresta.setDirecionada(isDirecionada);
            
            // Adicionar aresta ao grafo
            grafo.getArestas().add(aresta);
            
            // Se for grafo não direcionado, criar aresta reversa
            if (!isDirecionada) {
                ArestaDOT arestaReversa = new ArestaDOT();
                arestaReversa.setOrigem(destino);
                arestaReversa.setDestino(origem);
                arestaReversa.setDirecionada(false);
                grafo.getArestas().add(arestaReversa);
            }
        }
    }
    
    private VerticeDOT obterOuCriarVertice(String nome, Map<String, VerticeDOT> mapaVertices, GrafoDOT grafo) {
        if (!mapaVertices.containsKey(nome)) {
            VerticeDOT vertice = new VerticeDOT();
            vertice.setNome(nome);
            mapaVertices.put(nome, vertice);
            grafo.getVertices().add(vertice);
        }
        return mapaVertices.get(nome);
    }
    
    public void imprimirGrafo(GrafoDOT grafo) {
        if (temErros) {
            return;
        }

        System.out.println("=====================================");
        System.out.println("Nome: " + grafo.getNome());
        System.out.println("Tipo: " + (grafo.isDirecionado() ? "Direcionado" : "Não Direcionado"));
        System.out.println("Vértices: " + grafo.getVertices().size());
        System.out.println("Arestas: " + grafo.getArestas().size());
        
        System.out.println("\nVértices:");
        for (VerticeDOT v : grafo.getVertices()) {
            System.out.println("- " + v.getNome());
        }
        
        System.out.println("\nArestas:");
        for (ArestaDOT a : grafo.getArestas()) {
            String simbolo = a.isDirecionada() ? " -> " : " -- ";
            System.out.println("- " + a.getOrigem().getNome() + simbolo + a.getDestino().getNome());
        }
        System.out.println("=====================================");
    }
    
    public boolean temErros() {
        return temErros;
    }
}