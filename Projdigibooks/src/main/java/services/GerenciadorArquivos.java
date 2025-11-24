package services;

import models.Categoria;
import models.Livro;
import models.Movimentacao;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivos {

    private static final String ARQ_CATEGORIAS = "categorias.txt";
    private static final String ARQ_LIVROS = "livros.txt";
    private static final String ARQ_MOVIMENTACOES = "movimentacoes.txt";

    // ----------------------- CATEGORIAS --------------------------
    public List<Categoria> carregarCategorias() {
        List<Categoria> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARQ_CATEGORIAS))) {

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] p = linha.split(";");

                int id = Integer.parseInt(p[0]);
                String descricao = p[1];

                lista.add(new Categoria(id, descricao));
            }
        } catch (Exception e) {
            // arquivo pode não existir
        }

        return lista;
    }

    public void salvarCategorias(List<Categoria> categorias) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQ_CATEGORIAS))) {
            for (Categoria c : categorias) {

                if (c.getIdCategoria() == 0) {
                    c.setIdCategoria(gerarNovoId(ARQ_CATEGORIAS));
                }

                pw.println(
                        c.getIdCategoria() + ";" +
                        c.getDescricao()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------------- LIVROS --------------------------
    public List<Livro> carregarLivros() {
        List<Livro> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARQ_LIVROS))) {

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] p = linha.split(";");

                int idLivro = Integer.parseInt(p[0]);
                String titulo = p[1];
                String autor = p[2];
                int idCategoria = Integer.parseInt(p[3]);
                int ano = Integer.parseInt(p[4]);
                String status = p[5];

                lista.add(new Livro(idLivro, titulo, autor, idCategoria, ano, status));
            }
        } catch (Exception e) {
            // arquivo pode não existir
        }

        return lista;
    }

    public void salvarLivros(List<Livro> livros) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQ_LIVROS))) {
            for (Livro l : livros) {

                if (l.getIdLivro() == 0) {
                    l.setIdLivro(gerarNovoId(ARQ_LIVROS));
                }

                pw.println(
                        l.getIdLivro() + ";" +
                        l.getTitulo() + ";" +
                        l.getAutor() + ";" +
                        l.getIdCategoria() + ";" +
                        l.getAnoPublicacao() + ";" +
                        l.getStatus()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------------- MOVIMENTAÇÕES --------------------------
    public List<Movimentacao> carregarMovimentacoes() {
        List<Movimentacao> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARQ_MOVIMENTACOES))) {

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] p = linha.split(";");

                int idMov = Integer.parseInt(p[0]);
                int idLivro = Integer.parseInt(p[1]);
                String tipo = p[2];
                String responsavel = p[3];
                LocalDate data = LocalDate.parse(p[4]);
                String obs = p.length == 6 ? p[5] : "";

                lista.add(new Movimentacao(idMov, idLivro, tipo, responsavel, data, obs));
            }
        } catch (Exception e) {
            // arquivo pode não existir
        }

        return lista;
    }

    public void salvarMovimentacoes(List<Movimentacao> movs) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQ_MOVIMENTACOES))) {
            for (Movimentacao m : movs) {

                if (m.getIdMovimentacao() == 0) {
                    m.setIdMovimentacao(gerarNovoId(ARQ_MOVIMENTACOES));
                }

                pw.println(
                        m.getIdMovimentacao() + ";" +
                        m.getIdLivro() + ";" +
                        m.getTipoMovimentacao() + ";" +
                        m.getResponsavel() + ";" +
                        m.getData() + ";" +
                        m.getObservacao()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------------- GERAR NOVO ID --------------------------
    private int gerarNovoId(String arquivo) {
        int maior = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] p = linha.split(";");
                int id = Integer.parseInt(p[0]);

                if (id > maior) maior = id;
            }

        } catch (Exception e) {
            // arquivo não existe, começa em 1
        }

        return maior + 1;
    }
}