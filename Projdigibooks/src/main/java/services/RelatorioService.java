// Implementar RelatorioService.java - Parte 1
package services;

import models.Livro;
import models.Movimentacao;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class RelatorioService {

    // 1. Livros disponíveis
    public List<Livro> livrosDisponiveis(List<Livro> livros) {
        return livros.stream()
                .filter(l -> l.getStatus().equalsIgnoreCase("Disponível"))
                .collect(Collectors.toList());
    }

    // 2. Histórico de um livro
    public List<Movimentacao> historicoDoLivro(int idLivro, List<Movimentacao> movs) {
        return movs.stream()
                .filter(m -> m.getIdLivro() == idLivro)
                .collect(Collectors.toList());
    }

    // 3. Quantidade de empréstimos em um mês específico
    public long emprestimosPorMes(List<Movimentacao> movs, int mes, int ano) {
        return movs.stream()
                .filter(m -> m.getTipoMovimentacao().equalsIgnoreCase("Empréstimo"))
                .filter(m -> m.getData().getMonthValue() == mes)
                .filter(m -> m.getData().getYear() == ano)
                .count();
    }

    // 4. Livros mais emprestados no ano
    public Map<Integer, Long> livrosMaisEmprestados(List<Movimentacao> movs, int ano) {
        return movs.stream()
                .filter(m -> m.getTipoMovimentacao().equalsIgnoreCase("Empréstimo"))
                .filter(m -> m.getData().getYear() == ano)
                .collect(Collectors.groupingBy(
                        Movimentacao::getIdLivro,
                        Collectors.counting()
                ));
    }

    // 5. Livros perdidos ou danificados
    public List<Livro> livrosPerdidosOuDanificados(List<Livro> livros) {
        return livros.stream()
                .filter(l -> l.getStatus().equalsIgnoreCase("Perdido") ||
                             l.getStatus().equalsIgnoreCase("Danificado"))
                .collect(Collectors.toList());
    }

    // 6. Relatório de empréstimos por categoria
    public Map<Integer, Long> emprestimosPorCategoria(List<Movimentacao> movs, List<Livro> livros) {

        return movs.stream()
                .filter(m -> m.getTipoMovimentacao().equalsIgnoreCase("Empréstimo"))
                .collect(Collectors.groupingBy(m -> {

                    Livro livro = livros.stream()
                            .filter(l -> l.getIdLivro() == m.getIdLivro())
                            .findFirst()
                            .orElse(null);

                    return livro != null ? livro.getIdCategoria() : -1;

                }, Collectors.counting()));
    }
}
