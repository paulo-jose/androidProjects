package com.example.paulo.ahpplataforme.model;

import java.util.ArrayList;

/**
 * Created by PAULO on 18/09/2016.
 */
public class Alternativa {

    private int id;
    private String nome;
    private ArrayList<Criterio> criterios;


    public Alternativa() {
        this.id = 0;
        this.nome = "";
        this.criterios = new ArrayList<>();
    }

    public Alternativa(int id, String nome, ArrayList<Criterio> criterios) {
        this.id = id;
        this.nome = nome;
        this.criterios = criterios;
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

    public ArrayList<Criterio> getCriterios() {
        return criterios;
    }

    public void setCriterios(ArrayList<Criterio> criterios) {
        this.criterios = criterios;
    }
}
