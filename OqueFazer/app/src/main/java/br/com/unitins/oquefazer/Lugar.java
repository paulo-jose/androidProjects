package br.com.unitins.oquefazer;

import java.util.ArrayList;

/**
 * Created by Paulo on 26/05/2016.
 */
public class Lugar {

    private String icon = null;
    private String titulo = null;
    private ArrayList<String> opcoes = null;

    public Lugar(String icon, String titulo, ArrayList<String> opcoes) {
        this.icon = icon;
        this.titulo = titulo;
        this.opcoes = opcoes;
    }

    public Lugar() {
        this.icon = "";
        this.titulo = "";
        this.opcoes = new ArrayList<>();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<String> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(ArrayList<String> opcoes) {
        this.opcoes = opcoes;
    }
}
