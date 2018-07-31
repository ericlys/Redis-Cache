package com.test.beans;

public class Produto {
    private int codigo;
    private String description;
    private float price;

    public Produto(int codigo, String description, float price) {
        this.codigo = codigo;
        this.description = description;
        this.price = price;
    }

    public Produto() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo=" + codigo +
                ", descrição='" + description + '\'' +
                ", preço=" + price +
                '}';
    }
}