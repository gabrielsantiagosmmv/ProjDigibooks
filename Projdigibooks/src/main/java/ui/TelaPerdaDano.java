package ui;

import models.Livro;
import models.Movimentacao;
import services.GerenciadorArquivos;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class TelaPerdaDano extends JFrame {
    private GerenciadorArquivos gerenciador;
    private List<Livro> livros;
    private List<Movimentacao> movimentacoes;
    private JComboBox<Livro> comboLivros;
    private JComboBox<String> comboTipo;
    private JTextField txtResponsavel, txtObservacao;
    private JButton btnRegistrar, btnCancelar;

    public TelaPerdaDano() {
        gerenciador = new GerenciadorArquivos();
        inicializarComponentes();
        carregarDados();
    }

    private void inicializarComponentes() {
        setTitle("Registrar Perda/Dano");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Livro:"));
        comboLivros = new JComboBox<>();
        panel.add(comboLivros);

        panel.add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(new String[]{"Perda", "Danificado"});
        panel.add(comboTipo);

        panel.add(new JLabel("Responsável:"));
        txtResponsavel = new JTextField();
        panel.add(txtResponsavel);

        panel.add(new JLabel("Observação:"));
        txtObservacao = new JTextField();
        panel.add(txtObservacao);

        btnRegistrar = new JButton("Registrar");
        btnCancelar = new JButton("Cancelar");

        JPanel panelBotoes = new JPanel(new FlowLayout());
        panelBotoes.add(btnRegistrar);
        panelBotoes.add(btnCancelar);

        add(panel, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);

        btnRegistrar.addActionListener(e -> registrarPerdaDano());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void carregarDados() {
        livros = gerenciador.carregarLivros();
        movimentacoes = gerenciador.carregarMovimentacoes();
        atualizarComboLivros();
    }

    private void atualizarComboLivros() {
        comboLivros.removeAllItems();
        for (Livro livro : livros) {
            if (!"Perdido".equals(livro.getStatus()) && !"Danificado".equals(livro.getStatus())) {
                comboLivros.addItem(livro);
            }
        }
    }

    private void registrarPerdaDano() {
        Livro livroSelecionado = (Livro) comboLivros.getSelectedItem();
        String tipo = (String) comboTipo.getSelectedItem();
        String responsavel = txtResponsavel.getText().trim();
        String observacao = txtObservacao.getText().trim();

        if (livroSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um livro!");
            return;
        }

        if (responsavel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o nome do responsável!");
            return;
        }

        // Atualizar status do livro
        livroSelecionado.setStatus(tipo);
        gerenciador.salvarLivros(livros);

        // Registrar movimentação
        Movimentacao movimentacao = new Movimentacao(
            0, 
            livroSelecionado.getIdLivro(), 
            tipo, 
            responsavel, 
            LocalDate.now(), 
            observacao
        );
        movimentacoes.add(movimentacao);
        gerenciador.salvarMovimentacoes(movimentacoes);

        JOptionPane.showMessageDialog(this, 
            tipo + " registrado com sucesso!\n" +
            "Livro: " + livroSelecionado.getTitulo() + "\n" +
            "Status atualizado para: " + tipo);

        dispose();
    }
}