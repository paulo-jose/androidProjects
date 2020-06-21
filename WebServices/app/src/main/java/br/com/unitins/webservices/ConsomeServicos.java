package br.com.unitins.webservices;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Paulo on 18/05/2016.
 */
public class ConsomeServicos extends AsyncTask <String, Void, ArrayList<ModeloDadosEmpresa>> {

    ProgressDialog vrProgresso = null;
    TelaInicial vrTela = null;
    OkHttpClient vrClienteHttp = new OkHttpClient();
    Gson vrGson = new Gson();
    ArrayList<ModeloDadosEmpresa> vrListEmpresa = null;
    ArrayList<Bitmap> bitmaps = null;
    Response response = null;

    public ConsomeServicos(TelaInicial vrTela) {
        this.vrTela = vrTela;
    }

    //Metodo prepartorio
    public void onPreExecute()
    {
        vrProgresso = new ProgressDialog(vrTela);
        vrProgresso.setCancelable(false);
        vrProgresso.setCanceledOnTouchOutside(false);
        vrProgresso.setMessage("Baixando imagem ...");
        vrProgresso.setTitle("Agarde!");
        vrProgresso.show();
    }


    @Override
    protected ArrayList<ModeloDadosEmpresa> doInBackground(String... params) {

        bitmaps = new ArrayList<>();

        try
        {
            //Busca no servidor a String contendo o padrao Json

            Request request = new Request.Builder().url(params[0]).build();
            response = vrClienteHttp.newCall(request).execute();
            String sJson = response.body().string();

            //Transforma o padrao Json obtido em um \Array de objeto
            vrListEmpresa = vrGson.fromJson(sJson, new TypeToken<ArrayList<ModeloDadosEmpresa>>(){}.getType());


            for(ModeloDadosEmpresa aux: vrListEmpresa)
            {
                bitmaps.add(baixaImagem(new URL(aux.getIcone())));
            }
        }
        catch (Exception e)
        {

        }
        Log.i("info", vrListEmpresa.toString());
        return vrListEmpresa;

    }

    //Metodo chamado apos o termino da execucao da Thread
    public void onPostExecute(ArrayList<ModeloDadosEmpresa> listas)
    {
        vrProgresso.dismiss();
        vrTela.atualizarLista();
    }

    public ArrayList<ModeloDadosEmpresa> getVrListEmpresa() {
        return vrListEmpresa;
    }

    public void setVrListEmpresa(ArrayList<ModeloDadosEmpresa> vrListEmpresa) {
        this.vrListEmpresa = vrListEmpresa;
    }

    public Bitmap baixaImagem(URL... endereco) {

        URL url = null;


        Bitmap bitmap = null;
        try {

            HttpURLConnection conexao = (HttpURLConnection) endereco[0].openConnection();
            if(conexao == null)
            {
                return null;
            }

            conexao.setDoOutput(false);
            conexao.setInstanceFollowRedirects(false);
            InputStream inputStream = conexao.getInputStream();

            bitmap = BitmapFactory.decodeStream(conexao.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }


        return bitmap;
    }
}
