package com.example.paulo.ahpplataforme;

import android.support.v7.widget.RecyclerView;

/**
 * Created by PAULO on 06/07/2016.
 */
public class ParCriterios  {

    private String nomeCriterio1;
    private String nomeCriterio2;
    private double pesoCriterio1;
    private double pesoCriterio2;

    public ParCriterios() {
        this.nomeCriterio1 = "";
        this.nomeCriterio2 = "";
        this.pesoCriterio1 = 0.0;
        this.pesoCriterio2 = 0.0;
    }

    public ParCriterios(String nomeCriterio1, String nomeCriterio2, double pesoCriterio1, double pesoCriterio2) {
        this.nomeCriterio1 = nomeCriterio1;
        this.nomeCriterio2 = nomeCriterio2;
        this.pesoCriterio1 = pesoCriterio1;
        this.pesoCriterio2 = pesoCriterio2;
    }

    public String getNomeCriterio1() {
        return nomeCriterio1;
    }

    public void setNomeCriterio1(String nomeCriterio1) {
        this.nomeCriterio1 = nomeCriterio1;
    }

    public String getNomeCriterio2() {
        return nomeCriterio2;
    }

    public void setNomeCriterio2(String nomeCriterio2) {
        this.nomeCriterio2 = nomeCriterio2;
    }

    public double getPesoCriterio1() {
        return pesoCriterio1;
    }

    public void setPesoCriterio1(double pesoCriterio1) {
        this.pesoCriterio1 = pesoCriterio1;
    }

    public double getPesoCriterio2() {
        return pesoCriterio2;
    }

    public void setPesoCriterio2(double pesoCriterio2) {
        this.pesoCriterio2 = pesoCriterio2;
    }
}


