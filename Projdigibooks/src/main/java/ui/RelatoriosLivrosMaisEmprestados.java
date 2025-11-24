package ui;

import models.Livro;
import models.Categoria;
import services.GerenciadorArquivos;
import services.RelatorioService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class RelatoriosLivrosMaisEmprestados extends JFrame {
    private GerenciadorArquivos gerenciador;
    private RelatorioService relatorioService;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JSpinner spinnerAno;

    public RelatoriosLivrosMaisEmprestados() {
        gerenciador = new GerenciadorArquivos();
        relatorioService = new RelatorioService();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Relatório - Livros Mais Emprestados");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel de seleção
        JPanel panelSelecao = new JPanel(new FlowLayout());
        panelSelecao.add(new JLabel("Ano:"));
        spinnerAno = new JSpinner(new SpinnerNumberModel(LocalDate.now().getYear(), 2000, 2030, 1));
        panelSelecao.add(spinnerAno);

        JButton btnGerar = new JButton("Gerar Relatório");
        btnGerar.addActionListener(e -> gerarRelatorio());
        panelSelecao.add(btnGerar);

        panel.add(panelSelecao, BorderLayout.NORTH);

        // Tabela
        modeloTabela = new DefaultTableModel(new String[]{
            "Posição", "ID Livro", "Título", "Autor", "Categoria", "Quantidade de Empréstimos"
        }, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }

    private void gerarRelatorio() {
        int ano = (int) spinnerAno.getValue();
        
        List<Livro> livros = gerenciador.carregarLivros();
        List<Categoria> categorias = gerenciador.carregarCategorias();
        List<models.Movimentacao> movimentacoes = gerenciador.carregarMovimentacoes();
        
        Map<Integer, Long> livrosEmprestados = relatorioService.livrosMaisEmprestados(movimentacoes, ano);

        modeloTabela.setRowCount(0);
        
        // Ordenar por quantidade (do maior para o menor)
        livrosEmprestados.entrySet().stream()
            .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
            .forEach(entry -> {
                int idLivro = entry.getKey();
                long quantidade = entry.getValue();
                
                Livro livro = encontrarLivroPorId(livros, idLivro);
                if (livro != null) {
                    String nomeCategoria = encontrarCategoriaPorId(categorias, livro.getIdCategoria());
                    modeloTabela.addRow(new Object[]{
                        modeloTabela.getRowCount() + 1,
                        livro.getIdLivro(),
                        livro.getTitulo(),
                        livro.getAutor(),
                        nomeCategoria,
                        quantidade
                    });
                }
            });
            
        setTitle("Livros Mais Emprestados - " + ano + " (Total: " + livrosEmprestados.size() + " livros)");
    }

    private Livro encontrarLivroPorId(List<Livro> livros, int id) {
        return livros.stream()
                .filter(l -> l.getIdLivro() == id)
                .findFirst()
                .orElse(null);
    }

    private String encontrarCategoriaPorId(List<Categoria> categorias, int id) {
        return categorias.stream()
                .filter(c -> c.getIdCategoria() == id)
                .map(Categoria::getDescricao)
                .findFirst()
                .orElse("Não encontrada");
    }
}