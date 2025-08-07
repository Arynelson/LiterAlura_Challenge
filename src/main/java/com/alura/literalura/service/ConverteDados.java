
package com.alura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {
    private ObjectMapper mapper = new ObjectMapper(); // O "mágico" do Jackson

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            // Tenta converter o JSON para a classe que foi passada como parâmetro
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            // Lança uma exceção se houver erro na conversão
            throw new RuntimeException(e);
        }
    }
}
