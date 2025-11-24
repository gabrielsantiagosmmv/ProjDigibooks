package ui;

import services.GerenciadorArquivos;
import services.RelatorioService;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class RelatorioEmprestimosMes extends JFrame {
    private GerenciadorArquivos gerenciador;
    private RelatorioService relatorioService;
    private JSpinner spinnerMes, spinnerAno;
    private JTextArea txtResultado;

    public RelatorioEmprestimosMes() {
        gerenciador = new GerenciadorArquivos();
        relatorioService = new RelatorioService();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Relatório - Empréstimos por Mês");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel de seleção
        JPanel panelSelecao = new JPanel(new GridLayout(2, 2, 5, 5));
        panelSelecao.add(new JLabel("Mês:"));
        spinnerMes = new JSpinner(new SpinnerNumberModel(LocalDate.now().getMonthValue(), 1, 12, 1));
        panelSelecao.add(spinnerMes);

        panelSelecao.add(new JLabel("Ano:"));
        spinnerAno = new JSpinner(new SpinnerNumberModel(LocalDate.now().getYear(), 2000, 2030, 1));
        panelSelecao.add(spinnerAno);

        JButton btnGerar = new JButton("Gerar Relatório");
        btnGerar.addActionListener(e -> gerarRelatorio());

        JPanel panelBotoes = new JPanel();
        panelBotoes.add(btnGerar);

        // Área de resultado
        txtResultado = new JTextArea(10, 40);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtResultado);

        panel.add(panelSelecao, BorderLayout.NORTH);
        panel.add(panelBotoes, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        add(panel);
    }

    private void gerarRelatorio() {
        int mes = (int) spinnerMes.getValue();
        int ano = (int) spinnerAno.getValue();

        List<models.Movimentacao> movimentacoes = gerenciador.carregarMovimentacoes();
        long quantidade = relatorioService.emprestimosPorMes(movimentacoes, mes, ano);

        String[] nomesMeses = {"", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
                              "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        String resultado = "📊 RELATÓRIO DE EMPRÉSTIMOS\n" +
                         "============================\n" +
                         "Mês: " + nomesMeses[mes] + " de " + ano + "\n" +
                         "Quantidade de empréstimos: " + quantidade + "\n" +
                         "============================";

        txtResultado.setText(resultado);
    }
}