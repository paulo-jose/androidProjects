package br.com.unitins.imc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class TelaGordo extends AppCompatActivity {


    ImageView vrImagem = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_gordo);

        vrImagem = (ImageView)findViewById(R.id.imageView);
        vrImagem.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.normal));

        Double resultado = getIntent().getExtras().getDouble("resultado");

        if(resultado < 17)
        {
            //labelResult.setText("Muito abaixo do peso");
            vrImagem.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.magro));

        }else if (resultado >= 17 && resultado <= 18.49)
        {

            //labelResult.setText("Abaixo do peso");
            vrImagem.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.magro));

        }else if (resultado >= 18.5 && resultado < 25)
        {
            //labelResult.setText("Peso normal");
            vrImagem.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.normal));

        }
        else if (resultado >= 25 && resultado < 30)
        {
            //labelResult.setText("Acima do peso");
            vrImagem.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gordo));

        }else if (resultado >= 30 && resultado < 35)
        {
           // labelResult.setText("Obsidade I");
            vrImagem.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gordo));

        }else if (resultado >= 35 && resultado < 40)
        {
            //labelResult.setText("Obsidade II - Severa");
            vrImagem.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gordo));

        }
        else
        {
           // labelResult.setText("Obsidade III - mórbida");
            vrImagem.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gordo));

        }

    }
}
