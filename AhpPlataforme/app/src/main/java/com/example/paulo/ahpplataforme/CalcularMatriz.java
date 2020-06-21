package com.example.paulo.ahpplataforme;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.paulo.ahpplataforme.DAO.DBManager;
import com.example.paulo.ahpplataforme.model.Alternativa;
import com.example.paulo.ahpplataforme.model.AlternativaCriterio;
import com.example.paulo.ahpplataforme.model.Criterio;
import com.example.paulo.ahpplataforme.view.TelaLista;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PAULO on 16/07/2016.
 */
public class CalcularMatriz extends AsyncTask<List<Double>, Void, Double[][]> {

    Double[][] mat;
    List<Double> listaSoma;
    List<Double> autoVetor;
    List<Double[][]> listaMatriz;
    List<Double> vetorResultante;
    List<AlternativaCriterio> listaAlternativaCriterio;
    List<Criterio> listaCriterio;
    List<Alternativa> listaAlternativa;
    ProgressDialog vrProgresso = null;
    TelaLista contexto = null;
    int tam = 0;
    List<Double[][]> matrizs;
    List<List<Double>> pesosCriterios;
    DBManager bd;

    Double[][] matPrecos;
    Double [][] matSGBD;
    Double[][] matSuportes;
    Double [][] matRuntime;
    Double [][] matPreferencia;


    final Double[] precos = {0.01, 0.05, 0.02, 0.02};
    final Double[] SGBDs = {4.0, 3.0, 4.0, 2.0};
    final Double[] suportes = {4.0, 2.0, 4.0, 2.0};
    final Double[] runTimes = {6.0, 4.0, 6.0, 4.0};

    public CalcularMatriz(TelaLista contexto, int tam){
        this.contexto = contexto;
        this.tam = tam;
        mat = new Double[tam][tam];
    }

    public void onPreExecute()
    {
        vrProgresso = new ProgressDialog(contexto);
        vrProgresso.setCancelable(false);
        vrProgresso.setCanceledOnTouchOutside(false);
        vrProgresso.setMessage("Calculando ...");
        vrProgresso.setTitle("Aguarde!");
        vrProgresso.show();

    }

    @Override
    protected Double[][] doInBackground(List<Double>... vet) {

        for (int i = 0; i < vet[0].size(); i++) {
            System.out.println("gral de importancia :  " + vet[0].get(i));
        }

        int k = 0;

        for (int i = 0; i < tam; i++) {

            for (int j = 0; j < tam; j++) {
                if (i == j) {
                    mat[i][j] = 1.0;
                } else if (i < j) {
                    mat[i][j] = vet[0].get(k);
                    k++;
                } else if (i > j) {
                    mat[i][j] = 1 / mat[j][i];
                }
            }
        }

        bd = new DBManager(contexto);
        listaAlternativa = bd.buscar();

        montarVetorPesos();

        listaSoma = somarElementos();
        imprimirMatriz();
        divisaoElementos(listaSoma);
        imprimirMatriz();
        gerarAutoVetorAhp();

        matrizs = new ArrayList<>();

        for(int i = 0; i < pesosCriterios.size(); i++ )
        {
               if(i == 0)
                   matrizs.add(gerarMatrizPromethee(pesosCriterios.get(i), false));
               else
                   matrizs.add(gerarMatrizPromethee(pesosCriterios.get(i), true));
        }

 //       matPrecos = gerarMatriz(precos, false);
 //       matSGBD = gerarMatriz(SGBDs, true);
 //       matSuportes = gerarMatriz(suportes, true);
 //       matRuntime = gerarMatriz(runTimes, true);

        //add lista de matriz
        listaMatriz = new ArrayList<>();
 //       listaMatriz.add(matPrecos);
 //       listaMatriz.add(matSGBD);
 //       listaMatriz.add(matSuportes);
 //       listaMatriz.add(matRuntime);


        for(int i = 0; i < matrizs.size(); i++)
        {
            listaMatriz.add(matrizs.get(i));
        }

        for (int i = 0; i < tam; i++)
            imprimirMatriz(listaMatriz.get(i));


        for (int indice = 0; indice < tam; indice++)
            aplicarPesos(listaMatriz.get(indice), indice);


        for (int i = 0; i < tam; i++)
            imprimirMatriz(listaMatriz.get(i));


        gerarPreferencias(listaMatriz);

        gerarRanking();

        imprimirMatriz(matPreferencia);

        return mat;
    }

    private void montarVetorPesos() {


        listaAlternativaCriterio = bd.buscarAlternativaCriterio();
        pesosCriterios = new ArrayList<>();
        List<Double> lista = null;
        List<List<Double>> aux = new ArrayList<>();
        listaCriterio = bd.buscarCriterios();


        for(int w = 0; w < tam; w ++) {

            for (int k = 0; k < listaAlternativa.size(); k++) {
                lista = new ArrayList<>();
                for (int i = 0; i < listaCriterio.size(); i++) {

                    for (int j = 0; j < listaAlternativaCriterio.size(); j++) {

                        if (listaAlternativaCriterio.get(j).getAlternativa().getId() == listaAlternativa.get(k).getId()) {
                            if (listaAlternativaCriterio.get(j).getCriterio().getId() == listaCriterio.get(i).getId() && listaAlternativaCriterio.get(j).getPeso() != 0.0) {
                                lista.add(listaAlternativaCriterio.get(j).getPeso());
                            }
                        }
                    }
                }
                aux.add(lista);
            }
        }

        System.out.println("Vetor de pesos !!!!!!!!!");



        for(int i = 0; i < aux.get(i).size(); i++ )
        {
            lista = new ArrayList<>();
            for(int j = 0; j < aux.get(j).size(); j++)
            {
                lista.add(aux.get(j).get(i));
                System.out.print(aux.get(j).get(i) + " ,");
            }
            System.out.println();
            pesosCriterios.add(lista);
        }

    }

    public void gerarRanking() {


        List<Double> vetorSomaColuna = new ArrayList<>();
        List<Double> vetorSomaLinha = new ArrayList<>();
        vetorResultante = new ArrayList<>();

        double somaLinha;
        double somaColuna;

        for (int i = 0; i < listaAlternativa.size(); i++) {
            somaLinha = 0;
            somaColuna = 0;
            for (int j = 0; j < listaAlternativa.size(); j++) {

                if (i != j) {
                    somaLinha += matPreferencia[i][j];
                    somaColuna += matPreferencia[j][i];
                }
            }
            vetorSomaLinha.add(somaLinha);
            vetorSomaColuna.add(somaColuna);
        }

        for (int k = 0; k < vetorSomaColuna.size(); k++) {
            vetorResultante.add(vetorSomaLinha.get(k) - vetorSomaColuna.get(k));
        }


        for (int l = 0; l < vetorResultante.size(); l++) {
            System.out.println(String.format("%.2f", vetorSomaLinha.get(l)) + "| " + String.format("%.2f", vetorSomaColuna.get(l)) + "| " + String.format("%.2f", vetorResultante.get(l)) + "|");
        }
    }

    public void gerarPreferencias(List<Double[][]> listaMatriz) {

        matPreferencia = new Double[listaAlternativa.size()][listaAlternativa.size()];

        double soma;

        for (int i = 0; i < listaAlternativa.size(); i++) {
            for (int j = 0; j < listaAlternativa.size(); j++) {
                if (i != j) {
                    soma = 0;

                    for (int k = 0; k < tam; k++)
                        soma += listaMatriz.get(k)[i][j];

                    matPreferencia[i][j] = soma;
                }

            }
        }

    }

    public void aplicarPesos(Double[][] matriz, int indice) {

        for (int i = 0; i < listaAlternativa.size(); i++) {
            for (int j = 0; j < listaAlternativa.size(); j++) {
                if (i != j)
                    matriz[i][j] = matriz[i][j] * autoVetor.get(indice);
            }
        }
    }

    public Double[][] gerarMatrizPromethee(List<Double> peso, boolean opcao) {

        Double[][] mat = new Double[listaAlternativa.size()][listaAlternativa.size()];

        if (opcao == true) {

            for (int i = 0; i < listaAlternativa.size(); i++) {
                for (int j = 0; j < listaAlternativa.size(); j++) {

                    if (i == j) {
                        mat[i][j] = null;
                    } else if (peso.get(i) > peso.get(j)) {
                        mat[i][j] = 1.0;
                    } else {
                        mat[i][j] = 0.0;
                    }
                }
            }
        } else {
            for (int i = 0; i < listaAlternativa.size(); i++) {
                for (int j = 0; j < listaAlternativa.size(); j++) {

                    if (i == j) {
                        mat[i][j] = null;
                    } else if (peso.get(i) < peso.get(j)) {
                        mat[i][j] = 1.0;
                    } else {
                        mat[i][j] = 0.0;
                    }
                }
                System.out.print(peso.get(i)+ " ,");
            }
        }

        return mat;
    }

    public void gerarAutoVetorAhp() {

        double somaLinha;
        autoVetor = new ArrayList<>();

        for (int i = 0; i < tam; i++) {
            somaLinha = 0;
            for (int j = 0; j < tam; j++) {
                somaLinha += mat[i][j];
            }

            autoVetor.add(i, somaLinha);
        }

        for (int i = 0; i < tam; i++) {
            System.out.println("Auto Vetor :  " + String.format("%.2f", autoVetor.get(i)));
        }
    }

    public void imprimirMatriz() {

        String[] criterios = {"PREÇO", "SGBD", "SUPORTE", "RUN-TIME"};

        for (int i = 0; i < criterios.length; i++) {
            System.out.print(criterios[i] + " |");
        }

        System.out.println();

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                System.out.print(String.format("%.2f", mat[i][j]) + " |");
            }
            System.out.println();
        }
        System.out.print("\n\n\n");
    }

    public void imprimirMatriz(Double mat[][]) {

        String[] alternativas = {"AWS", "GAE", "AZURE", "BLUEMIX"};

        for (int i = 0; i < alternativas.length; i++) {
            System.out.print(alternativas[i] + " |");
        }

        System.out.println();

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                System.out.print(String.format("%.2f", mat[i][j]) + " |");
            }
            System.out.println();
        }

        System.out.print("\n\n\n");
    }

    public void divisaoElementos(List<Double> listaSoma) {

        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                mat[j][i] = mat[j][i] / listaSoma.get(i);
            }
        }
    }

    public List<Double> somarElementos() {

        List listSoma = new ArrayList<Double>();

        double somaColuna;

        for (int i = 0; i < tam ; i++) {
            somaColuna = 0;
            for (int j = 0; j < tam; j++) {
                somaColuna += mat[j][i];
                listSoma.add(i, somaColuna);
            }
        }

        for (int i = 0; i < listaAlternativa.size(); i++) {
            System.out.println("soma :  " + String.format("%.2f", listSoma.get(i)));
        }

        return listSoma;
    }


    public void onPostExecute(Double[][] mat)
    {
        vrProgresso.dismiss();
        contexto.vetorResultado =  vetorResultante;
        contexto.chamarTelaGrafico();
    }


}

























