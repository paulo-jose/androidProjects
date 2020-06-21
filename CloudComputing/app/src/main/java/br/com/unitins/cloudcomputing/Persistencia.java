package br.com.unitins.cloudcomputing;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Paulo on 07/04/2016.
 */
public class Persistencia {

    public static String lerArquivo(String nomeArquivo, Activity contexto) {

        String linha = "";
        String resultado = "";

        try {
            FileReader arquivo = new FileReader(contexto.getFilesDir().getPath()+nomeArquivo);
            BufferedReader buffer = new BufferedReader(arquivo);

            while ((linha = buffer.readLine())!=null)
            {
                resultado += linha;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultado;

    }

    public static void gravaArquivo(String nomeArquivo, Activity contexto, String conteudo) {

        try {
            FileWriter arquivo = new FileWriter(contexto.getFilesDir().getPath()+nomeArquivo, false);
            arquivo.write(conteudo);
            arquivo.flush();
            arquivo.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
