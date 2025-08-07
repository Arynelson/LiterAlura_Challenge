package com.alura.literalura.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private Integer anoDeNascimento;
    private Integer anoDeFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    // Construtor padrão (exigido pelo JPA)
    public Autor() {}

    // Construtor para facilitar a criação
    public Autor(DadosAutor dadosAutor) {
        this.nome = dadosAutor.nome();
        this.anoDeNascimento = dadosAutor.anoDeNascimento();
        this.anoDeFalecimento = dadosAutor.anoDeFalecimento();
    }

    // --- GETTERS E SETTERS ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public void setAnoDeNascimento(Integer anoDeNascimento) {
        this.anoDeNascimento = anoDeNascimento;
    }

    public Integer getAnoDeFalecimento() {
        return anoDeFalecimento;
    }

    public void setAnoDeFalecimento(Integer anoDeFalecimento) {
        this.anoDeFalecimento = anoDeFalecimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        // ... (gere um toString() para facilitar a visualização)
        return "Autor: " + nome +
                "\nAno de Nascimento: " + anoDeNascimento +
                "\nAno de Falecimento: " + anoDeFalecimento +
                "\nLivros: " + livros.stream().map(Livro::getTitulo).toList() + "\n";
    }
}
