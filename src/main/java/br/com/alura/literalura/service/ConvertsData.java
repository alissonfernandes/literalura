package br.com.alura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertsData implements IConvertsData{

    private final ObjectMapper mapper;

    public ConvertsData() {
        mapper = new ObjectMapper();
    }

    @Override
    public <T> T getDataObject(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            System.out.println("Error - " + ConvertsData.class.getName() + ": " + e.getMessage());
            return null;
        }
    }
}
