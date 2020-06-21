package br.com.unitins.exemplocamara;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TelaInicial extends AppCompatActivity {

    private ImageView foto = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_inicial);

        foto = (ImageView)findViewById(R.id.imageView);
    }

    public void carregarCamera(View view)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    public void onActivityResult(int codigo, int resultado, Intent dados)
    {
        super.onActivityResult(codigo, resultado, dados);
        if(dados != null)
        {
            Bundle bundle = dados.getExtras();
            if (bundle != null)
            {
                Bitmap imagem = (Bitmap) bundle.get("data");
                foto.setImageBitmap(imagem);
            }
        }
    }
}
