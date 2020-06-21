package br.com.unitins.handler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.LogRecord;

public class Telaprincipal extends AppCompatActivity {

    public static final int ATUALIZAR_TELA = 1;
    public static TextView label = null;
    public static ImageView imageView = null;
    public static int i = 0;
    public static TrataMensagem mensagens = new TrataMensagem();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

       // label = (TextView)findViewById(R.id.textView);
        imageView = (ImageView)findViewById(R.id.imageView);

    }



    public static void atualizarLabel() {
   //     label.setText(i+"");
    }

    public static void atualizarImageView(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    public void trataBotao(View view)
    {
        new MinhaTread().start();
    }


}

class TrataMensagem extends Handler
{

    Bitmap bitmap;

   public void handleMessage(Message msg)
   {
       if(msg.what == Telaprincipal.ATUALIZAR_TELA)
       {
           Telaprincipal.atualizarLabel();
           Telaprincipal.atualizarImageView(bitmap);
       }

   }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}


class MinhaTread extends Thread implements Runnable
{

    Bitmap bitmap;
    Message message = new Message();
    String[] imagens = {
            "https://pbs.twimg.com/profile_images/616076655547682816/6gMRtQyY.jpg",
            "http://gradle.org/wp-content/uploads/2015/08/IOS.jpg",
            "http://s2.glbimg.com/0LMTGlw12407xLBee82klQcm1Hg=/695x0/s.glbimg.com/po/tt2/f/original/2014/05/29/windows-phone.jpg"
    };
    String[] nomes = {"Android", "IOS"};

    TrataMensagem mensagens = new TrataMensagem();
    public void run()
    {

        int imagemAtual = 0;
        while (true)
        {

            try {
                URL url = new URL(imagens[imagemAtual]);
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
                conexao.setDoOutput(false);
                conexao.setInstanceFollowRedirects(false);
                InputStream inputStream = conexao.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);

                Thread.sleep(1000);
                Message mensagem = new Message();
                mensagem.what = Telaprincipal.ATUALIZAR_TELA;
                mensagens.setBitmap(bitmap);
                mensagens.sendMessage(mensagem);

                imagemAtual++;
                if (imagemAtual >=imagens.length)
                {
                    imagemAtual = 0;
                };

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //USAR HTTP CONECTION PARA BAIXA DADOS IMAGEM
            //USAR BITMAP FACTORY PARA DECODIFICAR OS BYTES EM UM BITMAP
            //ENVIAR MSG PARA A INTERFACE ATUALIZAR O IMAGEVIEW
            //PERMACE EM LOOP

            //ENVIAR UMA MSG DIZENDO PARA ATUALIZAR A TELA PRINCIPAL
        }
    }




}

