package ui;

import models.Livro;
import models.Movimentacao;
import models.Categoria;
import services.GerenciadorArquivos;
import services.RelatorioService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RelatorioHistoricoLivro extends JFrame {
    private GerenciadorArquivos gerenciador;
    private RelatorioService relatorioService;
    private JComboBox<Livro> comboLivros;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public RelatorioHistoricoLivro() {
        gerenciador = new GerenciadorArquivos();
        relatorioService = new RelatorioService();
        inicializarComponentes();
        carregarDados();
    }

    private void inicializarComponentes() {
        setTitle("Relatório - Histórico por Livro");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel de seleção
        JPanel panelSelecao = new JPanel(new FlowLayout());
        panelSelecao.add(new JLabel("Selecione o livro:"));
        comboLivros = new JComboBox<>();
        panelSelecao.add(comboLivros);
        
        JButton btnGerar = new JButton("Gerar Relatório");
        btnGerar.addActionListener(e -> gerarRelatorio());
        panelSelecao.add(btnGerar);

        panel.add(panelSelecao, BorderLayout.NORTH);

        // Tabela
        modeloTabela = new DefaultTableModel(new String[]{
            "Data", "Tipo", "Responsável", "Observação"
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
        for (Livro livro : livros) {
            comboLivros.addItem(livro);
        }
    }

    private void gerarRelatorio() {
        Livro livroSelecionado = (Livro) comboLivros.getSelectedItem();
        if (livroSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um livro!");
            return;
        }

        List<Movimentacao> movimentacoes = gerenciador.carregarMovimentacoes();
        List<Movimentacao> historico = relatorioService.historicoDoLivro(
            livroSelecionado.getIdLivro(), movimentacoes);

        modeloTabela.setRowCount(0);
        for (Movimentacao mov : historico) {
            modeloTabela.addRow(new Object[]{
                mov.getData(),
                mov.getTipoMovimentacao(),
                mov.getResponsavel(),
                mov.getObservacao()
            });
        }

        setTitle("Histórico - " + livroSelecionado.getTitulo() + 
                " (Total: " + historico.size() + " movimentações)");
    }
}