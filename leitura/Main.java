package leitura;

import java.io.IOException;

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
            ParserDOT parser = new ParserDOT();
            System.out.println("Lendo arquivo: " + caminhoArquivo);
            
            GrafoDOT grafo = parser.parsearArquivo(caminhoArquivo);

            parser.imprimirGrafo(grafo);
            
            if (!parser.temErros()) {
                System.out.println("Parsing concluído com sucesso!");
            } else {
                System.out.println("Parsing concluído com erros!");
            }
            
        } catch (IOException e) {
            System.err.println("ERRO ao ler arquivo: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("ERRO inesperado: " + e.getMessage());
        }
    }
}
