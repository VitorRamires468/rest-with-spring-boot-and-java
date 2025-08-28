package br.com.vitorramires468.integrationtests.controllers.withyaml.mappers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import java.io.IOException;

public class YamlMapper {

    private final ObjectMapper yamlObjectMapper;

    public YamlMapper() {
        // Configurar YAML Factory
        YAMLFactory yamlFactory = new YAMLFactory()
                .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
                .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
                .enable(YAMLGenerator.Feature.INDENT_ARRAYS);

        yamlObjectMapper = new ObjectMapper(yamlFactory);

        yamlObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        yamlObjectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        yamlObjectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        yamlObjectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public String toYamlString(Object object) {
        try {
            return yamlObjectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao serializar objeto para YAML", e);
        }
    }

    public <T> T fromYamlString(String yaml, Class<T> tclass) {
        try {
            return yamlObjectMapper.readValue(yaml, tclass);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao desserializar YAML para objeto", e);
        }
    }

}