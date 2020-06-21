package br.com.unitins.oquefazer;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import br.com.unitins.oquefazer.googlePlace.Location;
import br.com.unitins.oquefazer.googlePlace.Example;
import br.com.unitins.oquefazer.googlePlace.Result;

/**
 * Created by Paulo on 31/05/2016.
 */
public class ConsomeServicos extends AsyncTask<String, Void, Example> {

    ProgressDialog vrProgresso = null;
    Lista vrTela = null;
    OkHttpClient vrClienteHttp = new OkHttpClient();
    Gson vrGson = new Gson();
    JSONArray locais = null;
    ArrayList<Bitmap> bitmaps = null;
    Response response = null;
    JSONArray latitudes = new JSONArray();
    Example lugares = null;
    Location local = null;
    boolean termino = false;

    public ConsomeServicos(Lista vrTela) {
        this.vrTela = vrTela;
    }

    //Metodo prepartorio
    public void onPreExecute()
    {
        vrProgresso = new ProgressDialog(vrTela);
        vrProgresso.setCancelable(false);
        vrProgresso.setCanceledOnTouchOutside(false);
        vrProgresso.setMessage("Carregando mapa ...");
        vrProgresso.setTitle("Agarde!");
        vrProgresso.show();
    }


    @Override
    protected Example doInBackground(String... params) {

        bitmaps = new ArrayList<>();

        try
        {
            //Busca no servidor a String contendo o padrao Json
            Request request = new Request.Builder().url(params[0]).build();
            response = vrClienteHttp.newCall(request).execute();
            String sJson = response.body().string();

        //   br.com.unitins.oquefazer.googlePlace.Location location = vrGson.fromJson( sJson, br.com.unitins.oquefazer.googlePlace.Location.class );
            local = vrGson.fromJson(sJson, br.com.unitins.oquefazer.googlePlace.Location.class );
            //Transforma o padrao Json obtido em um \Array de objeto
            lugares = vrGson.fromJson(sJson, Example.class);

          //  Log.i("info", "latitude: "+ object.getString("lat"));


        }
        catch (Exception e)
        {

        }
        //Log.i("info", vrListEmpresa.toString());



        return lugares;

    }

    //Metodo chamado apos o termino da execucao da Thread
    public void onPostExecute(Example lugares)
    {
        vrProgresso.dismiss();
        vrTela.chamarTelaMapa();
    }



    public JSONArray getLocais() {
        return locais;
    }

    public void setLocais(JSONArray locais) {
        this.locais = locais;
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


    public Example getLugares() {
        return lugares;
    }

    public void setLugares(Example lugares) {
        this.lugares = lugares;
    }
}
