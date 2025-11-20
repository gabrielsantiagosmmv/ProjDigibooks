// Implementar RelatorioService.java - Parte 1
package services;

import models.Livro;
import digibooks.models.*;
import java.util.List;
import java.util.stream.Collectors;

public class RelatorioService {
    private List<Livro> livros;
    private List<Movimentacao> movimentacoes;
    private List<Categoria> categorias;
    
    public RelatorioService(List<Livro> livros, List<Movimentacao> movimentacoes, List<Categoria> categorias) {
        this.livros = livros;
        this.movimentacoes = movimentacoes;
        this.categorias = categorias;
    }
    
    // 1. Livros disponíveis
    public List<Livro> livrosDisponiveis() {
        return livros.stream()
                .filter(l -> l.getStatus().equals("Disponível"))
                .collect(Collectors.toList());
    }
    
    // 2. Histórico de um livro
    public List<Movimentacao> historicoLivro(int idLivro) {
        return movimentacoes.stream()
                .filter(m -> m.getIdLivro() == idLivro)
                .collect(Collectors.toList());
    }
}
// Implementar RelatorioService.java - Parte 2
// 3. Empréstimos do mês
public long totalEmprestimosMes(int mes, int ano) {
    return movimentacoes.stream()
            .filter(m -> m.getTipoMovimentacao().equals("Empréstimo") &&
                    m.getData().getMonthValue() == mes &&
                    m.getData().getYear() == ano)
            .count();
}

// 5. Livros com problemas
public List<Livro> livrosPerdidosOuDanificados() {
    return livros.stream()
            .filter(l -> l.getStatus().equals("Perdido") || l.getStatus().equals("Danificado"))
            .collect(Collectors.toList());
}

// Métodos auxiliares...
private Livro encontrarLivroPorId(int idLivro) {
    return livros.stream()
            .filter(l -> l.getIdLivro() == idLivro)
            .findFirst()
            .orElse(null);
}