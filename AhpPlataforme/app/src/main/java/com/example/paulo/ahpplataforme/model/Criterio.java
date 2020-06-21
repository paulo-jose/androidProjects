package com.example.paulo.ahpplataforme.model;

/**
 * Created by PAULO on 18/09/2016.
 */
public class Criterio {

    private int id;
    private String nome;
    private double peso;

    public Criterio() {

        this.id = 0;
        this.nome = "";
        this.peso = 0.0;
    }

    public Criterio(int id, String nome, double peso) {
        this.id = id;
        this.nome = nome;
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
