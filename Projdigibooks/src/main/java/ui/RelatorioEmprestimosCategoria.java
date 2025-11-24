package ui;

import models.Categoria;
import models.Livro;
import services.GerenciadorArquivos;
import services.RelatorioService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class RelatorioEmprestimosCategoria extends JFrame {
    private GerenciadorArquivos gerenciador;
    private RelatorioService relatorioService;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public RelatorioEmprestimosCategoria() {
        gerenciador = new GerenciadorArquivos();
        relatorioService = new RelatorioService();
        inicializarComponentes();
        carregarDados();
    }

    private void inicializarComponentes() {
        setTitle("Relatório - Empréstimos por Categoria");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Título
        JLabel lblTitulo = new JLabel("📈 Empréstimos por Categoria Literária", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblTitulo, BorderLayout.NORTH);

        // Tabela
        modeloTabela = new DefaultTableModel(new String[]{
            "Categoria", "Quantidade de Empréstimos", "Percentual"
        }, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }

    private void carregarDados() {
        List<models.Movimentacao> movimentacoes = gerenciador.carregarMovimentacoes();
        List<Livro> livros = gerenciador.carregarLivros();
        List<Categoria> categorias = gerenciador.carregarCategorias();

        Map<Integer, Long> emprestimosPorCategoria = relatorioService.emprestimosPorCategoria(movimentacoes, livros);

        modeloTabela.setRowCount(0);
        
        long totalEmprestimos = emprestimosPorCategoria.values().stream().mapToLong(Long::longValue).sum();

        for (Map.Entry<Integer, Long> entry : emprestimosPorCategoria.entrySet()) {
            int idCategoria = entry.getKey();
            long quantidade = entry.getValue();
            double percentual = totalEmprestimos > 0 ? (quantidade * 100.0 / totalEmprestimos) : 0;

            String nomeCategoria = "Categoria não encontrada";
            for (Categoria cat : categorias) {
                if (cat.getIdCategoria() == idCategoria) {
                    nomeCategoria = cat.getDescricao();
                    break;
                }
            }

            modeloTabela.addRow(new Object[]{
                nomeCategoria,
                quantidade,
                String.format("%.1f%%", percentual)
            });
        }

        // Adicionar linha de total
        modeloTabela.addRow(new Object[]{
            "TOTAL",
            totalEmprestimos,
            "100%"
        });
    }
}