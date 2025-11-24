package ui;

import models.Livro;
import models.Categoria;
import services.GerenciadorArquivos;
import services.RelatorioService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RelatorioLivrosDisponiveis extends JFrame {
    private GerenciadorArquivos gerenciador;
    private RelatorioService relatorioService;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public RelatorioLivrosDisponiveis() {
        gerenciador = new GerenciadorArquivos();
        relatorioService = new RelatorioService();
        inicializarComponentes();
        carregarDados();
    }

    private void inicializarComponentes() {
        setTitle("Relatório - Livros Disponíveis");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Título
        JLabel lblTitulo = new JLabel("📚 Livros Disponíveis no Acervo", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblTitulo, BorderLayout.NORTH);

        // Tabela
        modeloTabela = new DefaultTableModel(new String[]{
            "ID", "Título", "Autor", "Categoria", "Ano", "Status"
        }, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botão fechar
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        panel.add(btnFechar, BorderLayout.SOUTH);

        add(panel);
    }

    private void carregarDados() {
        List<Livro> livros = gerenciador.carregarLivros();
        List<Categoria> categorias = gerenciador.carregarCategorias();
        List<Livro> livrosDisponiveis = relatorioService.livrosDisponiveis(livros);

        modeloTabela.setRowCount(0);
        for (Livro livro : livrosDisponiveis) {
            String nomeCategoria = "Não encontrada";
            for (Categoria cat : categorias) {
                if (cat.getIdCategoria() == livro.getIdCategoria()) {
                    nomeCategoria = cat.getDescricao();
                    break;
                }
            }
            modeloTabela.addRow(new Object[]{
                livro.getIdLivro(),
                livro.getTitulo(),
                livro.getAutor(),
                nomeCategoria,
                livro.getAnoPublicacao(),
                livro.getStatus()
            });
        }

        JOptionPane.showMessageDialog(this, 
            "Total de livros disponíveis: " + livrosDisponiveis.size());
    }
}