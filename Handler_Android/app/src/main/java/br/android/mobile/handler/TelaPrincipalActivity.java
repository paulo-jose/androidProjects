package br.android.mobile.handler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;

public class TelaPrincipalActivity extends AppCompatActivity
{
    public static final int ATUALIZAR_TELA = 1;
    public static TextView label = null;
    public static int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_principal);

        label = (TextView)findViewById(R.id.label);
    }

    static void atualizaLabel()
    {
        label.setText(i+"");
   }

    public void trataBotao(View v)
    {
       new MinhaTread().start();
    }
}

class TrataMensagem extends Handler
{
    public void handleMessage(Message msg)
    {
        if (msg.what == TelaPrincipalActivity.ATUALIZAR_TELA)
        {
            TelaPrincipalActivity.atualizaLabel();
        }
    }
}

class MinhaTread extends Thread
{
    String[] imagens = {
            "https://pbs.twimg.com/profile_images/616076655547682816/6gMRtQyY.jpg",
            "http://gradle.org/wp-content/uploads/2015/08/IOS.jpg"};

    TrataMensagem mensagens = new TrataMensagem();
    public void run()
    {
        int imagemAtual = 0;
        while(true)
        {

            Message mensagem = new Message();
            mensagem.what = TelaPrincipalActivity.ATUALIZAR_TELA;
            mensagens.sendMessage(mensagem);

            imagemAtual++;
            if (imagemAtual >=imagens.length)
            {
                imagemAtual = 0;
            }
        }
    }
}
