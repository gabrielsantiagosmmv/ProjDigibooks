package ui;

import models.Categoria;
import services.GerenciadorArquivos;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaCategorias extends JFrame {
    private GerenciadorArquivos gerenciador;
    private List<Categoria> categorias;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JTextField txtDescricao;
    private JButton btnAdicionar, btnEditar, btnExcluir, btnSalvar;

    public TelaCategorias() {
        gerenciador = new GerenciadorArquivos();
        inicializarComponentes();
        carregarDados();
    }

    private void inicializarComponentes() {
        setTitle("Gerenciamento de Categorias");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel de formulário
        JPanel panelForm = new JPanel(new GridLayout(1, 3, 5, 5));
        panelForm.add(new JLabel("Descrição:"));
        txtDescricao = new JTextField();
        panelForm.add(txtDescricao);
        
        btnAdicionar = new JButton("Adicionar");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");
        btnSalvar = new JButton("Salvar Alterações");

        JPanel panelBotoes = new JPanel(new FlowLayout());
        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnEditar);
        panelBotoes.add(btnExcluir);
        panelBotoes.add(btnSalvar);

        // Tabela
        modeloTabela = new DefaultTableModel(new String[]{"ID", "Descrição"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);

        // Adicionar componentes ao painel principal
        panel.add(panelForm, BorderLayout.NORTH);
        panel.add(panelBotoes, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        add(panel);

        // Listeners
        btnAdicionar.addActionListener(e -> adicionarCategoria());
        btnEditar.addActionListener(e -> editarCategoria());
        btnExcluir.addActionListener(e -> excluirCategoria());
        btnSalvar.addActionListener(e -> salvarAlteracoes());
    }

    private void carregarDados() {
        categorias = gerenciador.carregarCategorias();
        atualizarTabela();
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        for (Categoria cat : categorias) {
            modeloTabela.addRow(new Object[]{cat.getIdCategoria(), cat.getDescricao()});
        }
    }

    private void adicionarCategoria() {
        String descricao = txtDescricao.getText().trim();
        if (descricao.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite uma descrição para a categoria!");
            return;
        }

        Categoria novaCategoria = new Categoria(0, descricao);
        categorias.add(novaCategoria);
        salvarAlteracoes();
        txtDescricao.setText("");
        JOptionPane.showMessageDialog(this, "Categoria adicionada com sucesso!");
    }

    private void editarCategoria() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma categoria para editar!");
            return;
        }

        int id = (int) modeloTabela.getValueAt(selectedRow, 0);
        String novaDescricao = JOptionPane.showInputDialog(this, "Nova descrição:", 
                modeloTabela.getValueAt(selectedRow, 1));

        if (novaDescricao != null && !novaDescricao.trim().isEmpty()) {
            for (Categoria cat : categorias) {
                if (cat.getIdCategoria() == id) {
                    cat.setDescricao(novaDescricao.trim());
                    break;
                }
            }
            salvarAlteracoes();
        }
    }

    private void excluirCategoria() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma categoria para excluir!");
            return;
        }

        int id = (int) modeloTabela.getValueAt(selectedRow, 0);
        String descricao = (String) modeloTabela.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(this, 
                "Tem certeza que deseja excluir a categoria: " + descricao + "?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            categorias.removeIf(cat -> cat.getIdCategoria() == id);
            salvarAlteracoes();
        }
    }

    private void salvarAlteracoes() {
        gerenciador.salvarCategorias(categorias);
        carregarDados();
    }
}