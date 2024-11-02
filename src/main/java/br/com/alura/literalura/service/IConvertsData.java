package br.com.alura.literalura.service;

public interface IConvertsData {
    <T> T getDataObject(String json, Class<T> tClas);
}
