package com.example.paulo.ahpplataforme.model;

/**
 * Created by PAULO on 20/09/2016.
 */
public class AlternativaCriterio {

    private int id;
    private Alternativa alternativa;
    private Criterio criterio;
    private Double peso;

    public AlternativaCriterio() {
        this.id = 0;
        this.alternativa = new Alternativa();
        this.criterio = new Criterio();
        this.peso = 0.0;
    }

    public AlternativaCriterio(int id, Alternativa alternativa, Criterio criterio, double peso) {
        this.id = id;
        this.alternativa = alternativa;
        this.criterio = criterio;
        this.peso = peso;
    }

    public Alternativa getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(Alternativa alternativa) {
        this.alternativa = alternativa;
    }

    public Criterio getCriterio() {
        return criterio;
    }

    public void setCriterio(Criterio criterio) {
        this.criterio = criterio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
}
