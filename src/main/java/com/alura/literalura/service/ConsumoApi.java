package com.alura.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {

    public String obterDados(String endereco ) {
        // Cria um cliente HTTP com configurações padrão
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();



        // Cria uma requisição GET para o endereço fornecido
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        HttpResponse<String> response = null;
        try {
            // Envia a requisição e obtém a resposta como uma String
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            // Captura exceções de IO (rede) ou se a thread for interrompida
            throw new RuntimeException(e);
        }

        // Retorna o corpo da resposta (o JSON)
        String json = response.body();
        System.out.println("JSON retornado pela API:\n" + json);
        return json;
    }
}
