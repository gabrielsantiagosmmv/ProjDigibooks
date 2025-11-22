package models;

import java.time.LocalDate;

public class Movimentacao {
    private int idMovimentacao;
    private int idLivro;
    private String tipoMovimentacao; // "Empréstimo", "Devolução", "Perda", "Danificado"
    private String responsavel;
    private LocalDate data;
    private String observacao;

    public Movimentacao() {}

    public Movimentacao(int idMovimentacao, int idLivro, String tipoMovimentacao, String responsavel, LocalDate data, String observacao) {
        this.idMovimentacao = idMovimentacao;
        this.idLivro = idLivro;
        this.tipoMovimentacao = tipoMovimentacao;
        this.responsavel = responsavel;
        this.data = data;
        this.observacao = observacao;
    }

    // Getters e Setters
    public int getIdMovimentacao() { return idMovimentacao; }
    public void setIdMovimentacao(int idMovimentacao) { this.idMovimentacao = idMovimentacao; }

    public int getIdLivro() { return idLivro; }
    public void setIdLivro(int idLivro) { this.idLivro = idLivro; }

    public String getTipoMovimentacao() { return tipoMovimentacao; }
    public void setTipoMovimentacao(String tipoMovimentacao) { this.tipoMovimentacao = tipoMovimentacao; }

    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}