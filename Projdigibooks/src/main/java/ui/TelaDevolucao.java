package ui;

import models.Livro;
import models.Movimentacao;
import services.GerenciadorArquivos;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class TelaDevolucao extends JFrame {
    private GerenciadorArquivos gerenciador;
    private List<Livro> livros;
    private List<Movimentacao> movimentacoes;
    private JComboBox<Livro> comboLivros;
    private JTextField txtResponsavel, txtObservacao;
    private JButton btnDevolver, btnCancelar;

    public TelaDevolucao() {
        gerenciador = new GerenciadorArquivos();
        inicializarComponentes();
        carregarDados();
    }

    private void inicializarComponentes() {
        setTitle("Registrar Devolução");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Livro:"));
        comboLivros = new JComboBox<>();
        panel.add(comboLivros);

        panel.add(new JLabel("Responsável:"));
        txtResponsavel = new JTextField();
        panel.add(txtResponsavel);

        panel.add(new JLabel("Observação:"));
        txtObservacao = new JTextField();
        panel.add(txtObservacao);

        btnDevolver = new JButton("Registrar Devolução");
        btnCancelar = new JButton("Cancelar");

        JPanel panelBotoes = new JPanel(new FlowLayout());
        panelBotoes.add(btnDevolver);
        panelBotoes.add(btnCancelar);

        add(panel, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);

        btnDevolver.addActionListener(e -> registrarDevolucao());
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
            if ("Emprestado".equals(livro.getStatus())) {
                comboLivros.addItem(livro);
            }
        }
        
        if (comboLivros.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "Não há livros emprestados para devolução!");
        }
    }

    private void registrarDevolucao() {
        Livro livroSelecionado = (Livro) comboLivros.getSelectedItem();
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
        livroSelecionado.setStatus("Disponível");
        gerenciador.salvarLivros(livros);

        // Registrar movimentação
        Movimentacao movimentacao = new Movimentacao(
            0, 
            livroSelecionado.getIdLivro(), 
            "Devolução", 
            responsavel, 
            LocalDate.now(), 
            observacao
        );
        movimentacoes.add(movimentacao);
        gerenciador.salvarMovimentacoes(movimentacoes);

        JOptionPane.showMessageDialog(this, 
            "Devolução registrada com sucesso!\n" +
            "Livro: " + livroSelecionado.getTitulo() + "\n" +
            "Responsável: " + responsavel);

        dispose();
    }
}