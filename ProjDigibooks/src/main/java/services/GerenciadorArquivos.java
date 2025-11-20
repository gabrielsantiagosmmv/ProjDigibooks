// Implementar GerenciadorArquivos.java - Parte 1
package digibooks.services;

import digibooks.models.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivos {
    private static final String PASTA_DATA = "data/";
    
    // Métodos para Livros
    public static void salvarLivros(List<Livro> livros) throws IOException {
        // Implementar
    }
    
    public static List<Livro> carregarLivros() throws IOException {
        // Implementar
        return new ArrayList<>();
    }
}

// Implementar GerenciadorArquivos.java - Parte 2
// Métodos para Categorias
public static void salvarCategorias(List<Categoria> categorias) throws IOException {
    // Implementar
}

public static List<Categoria> carregarCategorias() throws IOException {
    // Implementar
    return new ArrayList<>();
}

// Métodos para Movimentações
public static void salvarMovimentacoes(List<Movimentacao> movimentacoes) throws IOException {
    // Implementar
}

public static List<Movimentacao> carregarMovimentacoes() throws IOException {
    // Implementar
    return new ArrayList<>();
}