package br.com.unitins.assync;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TelaInicial extends AppCompatActivity {

    ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_inicial);
        imagem = (ImageView) findViewById(R.id.imageView);
    }

    public void carregar(View view)
    {

        try {
            GerenciarDowload start = new GerenciarDowload(this);
            start.execute(new URL("http://www.agenciamercadocentral.com.br/app_agencia/icones/2.png"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}


    //CRIA UMA CLASSE DESCENDENTE DE ASYNCTASK
    //DEFINE O PARAMETRO ENTRAD, PROGRESSO, RESULTADO (RETORNO)
    class GerenciarDowload extends AsyncTask<URL, Integer, Bitmap>
    {

        TelaInicial vrTela = null;
        ProgressDialog vrProgresso = null;


        public GerenciarDowload(TelaInicial tela) {
            vrTela = tela;
        }

        //Metdo executado antes de iniciar a Thread. Interessante para
        //Chamar um componente que mostra ao usuario uma mensagem de aguardem
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
        //Metodo chamado durante o processamento (Thread). Retorna um objeto
        //Que sera desenvolvido metodo onPostExecute
        protected Bitmap doInBackground(URL... enderecos) {

            URL url = null;


            Bitmap bitmap = null;
            try {

                HttpURLConnection conexao = (HttpURLConnection) enderecos[0].openConnection();
                conexao.setDoOutput(false);
                conexao.setInstanceFollowRedirects(false);
                InputStream inputStream = conexao.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return bitmap;
        }

        public void onPostExecute(Bitmap resultado)
        {
            vrProgresso.dismiss();
            vrTela.imagem.setImageBitmap(resultado);
        }
    }

