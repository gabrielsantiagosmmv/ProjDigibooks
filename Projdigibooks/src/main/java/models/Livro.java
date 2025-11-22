package models;

public class Livro {
    private int idLivro;
    private String titulo;
    private String autor;
    private int idCategoria;
    private int anoPublicacao;
    private String status; // "Disponível", "Emprestado", "Perdido", "Danificado"

    public Livro() {}

    public Livro(int idLivro, String titulo, String autor, int idCategoria, int anoPublicacao, String status) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.autor = autor;
        this.idCategoria = idCategoria;
        this.anoPublicacao = anoPublicacao;
        this.status = status;
    }

    // Getters e Setters
    public int getIdLivro() { return idLivro; }
    public void setIdLivro(int idLivro) { this.idLivro = idLivro; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    public int getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(int anoPublicacao) { this.anoPublicacao = anoPublicacao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}