package ui;

public class BibliotecaGUI extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BibliotecaGUI.class.getName());

    public BibliotecaGUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuLivros = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu2.setText("Cadastros");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuLivros.setText("📚 Livros");
        jMenuLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuLivrosActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuLivros);

        jMenuItem6.setText("📁 Categorias");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Movimentações");

        jMenuItem7.setText("📖 Empréstimo");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem8.setText("↩️ Devolução");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuItem9.setText("⚠ Registrar Perda/Dano");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Relatorios");

        jMenuItem10.setText("✅ Livros Disponíveis");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuItem11.setText("📋 Histórico por Livro");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuItem12.setText("📅 Empréstimos por mês");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem12);

        jMenuItem13.setText("🏆 Livros Mais Emprestados");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem13);

        jMenuItem14.setText("❌ Livros Perdidos");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem14);

        jMenuItem15.setText("📈 Empréstimos por Categoria");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem15);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }

    private void jMenuLivrosActionPerformed(java.awt.event.ActionEvent evt) {
        TelaLivros telaLivros = new TelaLivros();
        telaLivros.setVisible(true);
        telaLivros.setLocationRelativeTo(this);
    }

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {
        TelaCategorias telaCat = new TelaCategorias();
        telaCat.setVisible(true);
        telaCat.setLocationRelativeTo(this);
    }

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {
        TelaEmprestimo telaEmprestimo = new TelaEmprestimo();
        telaEmprestimo.setVisible(true);
        telaEmprestimo.setLocationRelativeTo(this);
    }

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {
        TelaDevolucao telaDevolucao = new TelaDevolucao();
        telaDevolucao.setVisible(true);
        telaDevolucao.setLocationRelativeTo(this);
    }

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {
        TelaPerdaDano telaPerdaDano = new TelaPerdaDano();
        telaPerdaDano.setVisible(true);
        telaPerdaDano.setLocationRelativeTo(this);
    }

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {
        RelatorioLivrosDisponiveis relatorio = new RelatorioLivrosDisponiveis();
        relatorio.setVisible(true);
        relatorio.setLocationRelativeTo(this);
    }

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {
        RelatorioHistoricoLivro relatorio = new RelatorioHistoricoLivro();
        relatorio.setVisible(true);
        relatorio.setLocationRelativeTo(this);
    }

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {
        RelatorioEmprestimosMes relatorio = new RelatorioEmprestimosMes();
        relatorio.setVisible(true);
        relatorio.setLocationRelativeTo(this);
    }

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {
        RelatoriosLivrosMaisEmprestados relatorio = new RelatoriosLivrosMaisEmprestados();
        relatorio.setVisible(true);
        relatorio.setLocationRelativeTo(this);
    }

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {
        RelatorioLivrosPerdidosDanificados relatorio = new RelatorioLivrosPerdidosDanificados();
        relatorio.setVisible(true);
        relatorio.setLocationRelativeTo(this);
    }

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {
        RelatorioEmprestimosCategoria relatorio = new RelatorioEmprestimosCategoria();
        relatorio.setVisible(true);
        relatorio.setLocationRelativeTo(this);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new BibliotecaGUI().setVisible(true));
    }

    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuLivros;
}