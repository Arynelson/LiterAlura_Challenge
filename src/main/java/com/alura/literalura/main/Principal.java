package com.alura.literalura.main;
import com.alura.literalura.model.Autor; // Importe o Autor
import com.alura.literalura.model.DadosAutor;
import com.alura.literalura.model.DadosLivro;
import com.alura.literalura.model.DadosResultados;
import com.alura.literalura.model.Livro; // Importe o Livro
import com.alura.literalura.repository.LivroRepository; // Importe o repositório
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Principal {


    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/";
    // Campo para armazenar o repositório
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    // Construtor que recebe o repositório
    @Autowired
    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() {
        // ... (o método exibeMenu continua o mesmo)
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    Escolha o numero da sua opção:
                    1- buscar livro pelo titulo
                    2- listar livros registrados
                    3- listar autores registrados
                    4- listar autores vivos em um determinado ano
                    5- listar livros em um determinado idioma
                    
                    0- sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    buscarLivroWeb();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados(); // Adicionar esta linha
                    break;
                case 4:
                    listarAutoresVivosEmAno(); // Adicionar esta linha
                    break;
                case 5:
                    listarLivrosPorIdioma(); // Adicionar esta linha
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarLivroWeb() {
        System.out.println("Digite o nome do livro para busca :");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + "?search=" + nomeLivro.replace(" ", "+"));

        DadosResultados dadosResultados = conversor.obterDados(json, DadosResultados.class);

        if (dadosResultados != null && !dadosResultados.resultados().isEmpty()) {
            DadosLivro dadosLivro = dadosResultados.resultados().get(0);
            DadosAutor dadosAutor = dadosLivro.autores().get(0);


            // Cria um objeto Autor com os dados da API
            Optional<Autor> autorOptional = autorRepository.findByNome(dadosAutor.nome());

            Autor autor;

            if (autorOptional.isPresent()) {
                autor = autorOptional.get();
            } else {
                autor = new Autor(dadosAutor);
                autor = autorRepository.save(autor);
            }
            // Cria um objeto Livro com os dados da API
            Livro livro = new Livro(dadosLivro);
            // Associa o autor ao livro
            livro.setAutor(autor);

            // Salva o livro (e o autor, por causa do CascadeType.ALL) no banco de dados
            try {
                livroRepository.save(livro);
            } catch (DataIntegrityViolationException e) {
                System.out.println("Livro já existe no nosso Banco de Dados: " + livro.getTitulo());

            }

            System.out.println("----- LIVRO ENCONTRADO E SALVO -----");
            System.out.println(livro); // Usando o toString() que criamos
            System.out.println("-------------------------------------\n");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }


    private void listarLivrosRegistrados() {
        List<Livro> livros = livroRepository.findAll(); // Busca todos os livros do banco

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado no banco de dados.");
        } else {
            System.out.println("----- LIVROS REGISTRADOS -----");
            // Ordena os livros por título para uma exibição mais organizada
            livros.stream()
                    .sorted(Comparator.comparing(Livro::getTitulo))
                    .forEach(System.out::println); // Usa o método toString() de Livro para imprimir
            System.out.println("------------------------------\n");
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll(); // Busca todos os autores

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado no banco de dados.");
        } else {
            System.out.println("----- AUTORES REGISTRADOS -----");
            autores.stream()
                    .sorted(Comparator.comparing(Autor::getNome)) // Ordena por nome
                    .forEach(System.out::println); // Usa o toString() de Autor
            System.out.println("-------------------------------\n");
        }
    }

    private void listarAutoresVivosEmAno() {
        System.out.println("Digite um ano para verificar os autores vivos:");
        try {
            var ano = leitura.nextInt();
            leitura.nextLine(); // Limpar o buffer

            List<Autor> autoresVivos = autorRepository.findAutoresVivosEmAno(ano);

            if (autoresVivos.isEmpty()) {
                System.out.println("Nenhum autor vivo encontrado para o ano de " + ano + ".");
            } else {
                System.out.println("----- AUTORES VIVOS EM " + ano + " -----");
                autoresVivos.stream()
                        .sorted(Comparator.comparing(Autor::getNome))
                        .forEach(System.out::println);
                System.out.println("----------------------------------\n");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número de ano válido.");
            leitura.nextLine(); // Limpar o buffer do scanner em caso de erro
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("Digite o idioma para a busca (ex: en, pt, es, fr):");
        var idioma = leitura.nextLine();

        List<Livro> livrosPorIdioma = livroRepository.findByIdioma(idioma);

        if (livrosPorIdioma.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma '" + idioma + "'.");
        } else {
            System.out.println("----- LIVROS NO IDIOMA '" + idioma.toUpperCase() + "' -----");
            livrosPorIdioma.stream()
                    .sorted(Comparator.comparing(Livro::getTitulo))
                    .forEach(System.out::println);
            System.out.println("----------------------------------\n");
        }
    }

}