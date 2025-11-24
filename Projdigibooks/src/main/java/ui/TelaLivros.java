package ui;

import models.Livro;
import models.Categoria;
import services.GerenciadorArquivos;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaLivros extends JFrame {
    private GerenciadorArquivos gerenciador;
    private List<Livro> livros;
    private List<Categoria> categorias;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JTextField txtTitulo, txtAutor, txtAno;
    private JComboBox<Categoria> comboCategorias;
    private JComboBox<String> comboStatus;
    private JButton btnAdicionar, btnEditar, btnExcluir, btnSalvar, btnPesquisar;

    public TelaLivros() {
        gerenciador = new GerenciadorArquivos();
        inicializarComponentes();
        carregarDados();
    }

    private void inicializarComponentes() {
        setTitle("Gerenciamento de Livros");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelForm.add(new JLabel("Título:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        txtTitulo = new JTextField();
        panelForm.add(txtTitulo, gbc);

        gbc.gridx = 2; gbc.gridy = 0; gbc.weightx = 0;
        panelForm.add(new JLabel("Autor:"), gbc);

        gbc.gridx = 3; gbc.gridy = 0; gbc.weightx = 1.0;
        txtAutor = new JTextField();
        panelForm.add(txtAutor, gbc);

        gbc.gridx = 4; gbc.gridy = 0; gbc.weightx = 0;
        panelForm.add(new JLabel("Ano:"), gbc);

        gbc.gridx = 5; gbc.gridy = 0; gbc.weightx = 0.5;
        txtAno = new JTextField();
        panelForm.add(txtAno, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelForm.add(new JLabel("Categoria:"), gbc);

        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        comboCategorias = new JComboBox<>();
        panelForm.add(comboCategorias, gbc);

        gbc.gridx = 2; gbc.gridy = 1; gbc.weightx = 0;
        panelForm.add(new JLabel("Status:"), gbc);

        gbc.gridx = 3; gbc.gridy = 1; gbc.weightx = 1.0;
        comboStatus = new JComboBox<>(new String[]{"Disponível", "Emprestado", "Perdido", "Danificado"});
        panelForm.add(comboStatus, gbc);

        btnAdicionar = new JButton("Adicionar");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");
        btnSalvar = new JButton("Salvar");
        btnPesquisar = new JButton("Pesquisar");

        JPanel panelBotoes = new JPanel(new FlowLayout());
        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnEditar);
        panelBotoes.add(btnExcluir);
        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnPesquisar);

        modeloTabela = new DefaultTableModel(new String[]{
            "ID", "Título", "Autor", "Categoria", "Ano", "Status"
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);

        JPanel panelTop = new JPanel(new BorderLayout());
        panelTop.add(panelForm, BorderLayout.NORTH);
        panelTop.add(panelBotoes, BorderLayout.SOUTH);

        panel.add(panelTop, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        btnAdicionar.addActionListener(e -> adicionarLivro());
        btnEditar.addActionListener(e -> editarLivro());
        btnExcluir.addActionListener(e -> excluirLivro());
        btnSalvar.addActionListener(e -> salvarAlteracoes());
        btnPesquisar.addActionListener(e -> pesquisarLivros());
    }

    private void carregarDados() {
        livros = gerenciador.carregarLivros();
        categorias = gerenciador.carregarCategorias();
        atualizarComboCategorias();
        atualizarTabela();
    }

    private void atualizarComboCategorias() {
        comboCategorias.removeAllItems();
        for (Categoria cat : categorias) comboCategorias.addItem(cat);
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        for (Livro livro : livros) {
            String categoriaNome = categorias.stream()
                    .filter(c -> c.getIdCategoria() == livro.getIdCategoria())
                    .map(Categoria::getDescricao)
                    .findFirst()
                    .orElse("Não encontrada");

            modeloTabela.addRow(new Object[]{
                livro.getIdLivro(),
                livro.getTitulo(),
                livro.getAutor(),
                categoriaNome,
                livro.getAnoPublicacao(),
                livro.getStatus()
            });
        }
    }

    private void adicionarLivro() {
        try {
            String titulo = txtTitulo.getText().trim();
            String autor = txtAutor.getText().trim();
            String anoText = txtAno.getText().trim();

            if (titulo.isEmpty() || autor.isEmpty() || anoText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                return;
            }

            int ano = Integer.parseInt(anoText);
            Categoria categoria = (Categoria) comboCategorias.getSelectedItem();
            String status = (String) comboStatus.getSelectedItem();

            if (categoria == null) {
                JOptionPane.showMessageDialog(this, "Selecione uma categoria!");
                return;
            }

            Livro novo = new Livro(0, titulo, autor, categoria.getIdCategoria(), ano, status);
            livros.add(novo);
            salvarAlteracoes();
            limparCampos();

            JOptionPane.showMessageDialog(this, "Livro adicionado!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ano deve ser um número válido!");
        }
    }

    private void editarLivro() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um livro para editar!");
            return;
        }

        int id = (int) modeloTabela.getValueAt(selectedRow, 0);
        Livro livro = encontrarLivroPorId(id);

        if (livro != null) {
            txtTitulo.setText(livro.getTitulo());
            txtAutor.setText(livro.getAutor());
            txtAno.setText(String.valueOf(livro.getAnoPublicacao()));

            for (int i = 0; i < comboCategorias.getItemCount(); i++) {
                Categoria cat = comboCategorias.getItemAt(i);
                if (cat.getIdCategoria() == livro.getIdCategoria()) {
                    comboCategorias.setSelectedIndex(i);
                    break;
                }
            }

            comboStatus.setSelectedItem(livro.getStatus());
            livros.remove(livro);
        }
    }

    private void excluirLivro() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um livro para excluir!");
            return;
        }

        int id = (int) modeloTabela.getValueAt(selectedRow, 0);
        String titulo = (String) modeloTabela.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja excluir o livro: " + titulo + "?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            livros.removeIf(livro -> livro.getIdLivro() == id);
            salvarAlteracoes();
        }
    }

    private void pesquisarLivros() {
        String termo = JOptionPane.showInputDialog(this, "Digite o termo de pesquisa (título, autor ou categoria):");
        if (termo == null || termo.trim().isEmpty()) {
            atualizarTabela();
            return;
        }

        termo = termo.toLowerCase().trim();
        modeloTabela.setRowCount(0);

        for (Livro livro : livros) {
            String nomeCategoria = categorias.stream()
                    .filter(c -> c.getIdCategoria() == livro.getIdCategoria())
                    .map(Categoria::getDescricao)
                    .findFirst()
                    .orElse("Não encontrada");

            if (livro.getTitulo().toLowerCase().contains(termo) ||
                livro.getAutor().toLowerCase().contains(termo) ||
                nomeCategoria.toLowerCase().contains(termo)) {

                modeloTabela.addRow(new Object[]{
                        livro.getIdLivro(),
                        livro.getTitulo(),
                        livro.getAutor(),
                        nomeCategoria,
                        livro.getAnoPublicacao(),
                        livro.getStatus()
                });
            }
        }
    }

    private Livro encontrarLivroPorId(int id) {
        for (Livro livro : livros) {
            if (livro.getIdLivro() == id) return livro;
        }
        return null;
    }

    private void salvarAlteracoes() {
        gerenciador.salvarLivros(livros);
        carregarDados();
    }

    private void limparCampos() {
        txtTitulo.setText("");
        txtAutor.setText("");
        txtAno.setText("");
        comboCategorias.setSelectedIndex(0);
        comboStatus.setSelectedIndex(0);
    }
}
