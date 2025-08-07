package com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne
    private Autor autor;

    private String idioma;
    private Integer numeroDeDownloads;

    // Construtor padrão
    public Livro() {}

    // Construtor principal
    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();
        this.idioma = String.join(", ", dadosLivro.idiomas()); // Pega o primeiro idioma da lista
        this.numeroDeDownloads = dadosLivro.numeroDeDownloads();
    }

    // --- GETTERS E SETTERS ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDeDownloads() {
        return numeroDeDownloads;
    }

    public void setNumeroDeDownloads(Integer numeroDeDownloads) {
        this.numeroDeDownloads = numeroDeDownloads;
    }
    // --- FIM DOS GETTERS E SETTERS ---

    @Override
    public String toString() {
        // ... (gere um toString())
        return "----- LIVRO -----" +
                "\nTítulo: " + titulo +
                "\nAutor: " + (autor != null ? autor.getNome() : "Desconhecido") +
                "\nIdioma: " + idioma +
                "\nNúmero de Downloads: " + numeroDeDownloads +
                "\n---------------\n";
    }
}
