package br.com.unitins.mapfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TelaPrincipal extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_principal);
        FragmentManager gerenciadoFragmento = getSupportFragmentManager();
        Fragment fragemento = (Fragment)gerenciadoFragmento.findFragmentById(R.id.fragmento);

    }
}
