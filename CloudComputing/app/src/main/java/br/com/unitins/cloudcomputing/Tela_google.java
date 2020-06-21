package br.com.unitins.cloudcomputing;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Tela_google extends AppCompatActivity {

    TextView texto = null;
    SearchView sv = null;
    String resultado = "";
    Persistencia persistencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_google);

        sv = (SearchView) findViewById(R.id.searchView);
        sv.setOnQueryTextListener(new SearchFilter());

        texto = (TextView) findViewById(R.id.textConteudo);
        carregar();
        texto.setText(resultado);

    }

    private class SearchFilter implements SearchView.OnQueryTextListener {
        @Override
        public boolean onQueryTextSubmit(String query) {
            buscarTexto(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    }

    private void carregar() {

        persistencia.gravaArquivo("google.txt", this, "O Google App Engine é uma plataforma de Computação em nuvem para desenvolver e hospedar aplicações web na infraestrutura do Google. Foi inicialmente lançado como versão preliminar (beta) em Abril de 2008. [1]\n" +
                "O Google App Engine é uma tecnologia no modelo Plataforma como Serviço. Ele virtualiza aplicações em múltiplos servidores, provendo hardware, conectividade, sistema operacional e serviços de software. O Google App Engine pode ser usado gratuitamente até um determinado nível de consumo de recursos. A partir daí, tarifas adicionais são cobradas pelo consumo recursos (armazenamento, banda de rede, ciclos de CPU, etc.) da aplicação.[2]\n" +
                "Google Cloud SQL\n" +
                "Em outubro 2011, o Google previews um zero de manutenção de banco de dados SQL, JDBC e que suporta DB-API. [3] . Este serviço permite criar, configurar e usar bancos de dados relacionais com os aplicativos App Engine. Motor de banco de dados é MySql versão 5.1.59 eo tamanho do banco de dados não pode ser maior do que 10GB [4] .");

        resultado = persistencia.lerArquivo("google.txt", this);
    }


    private void buscarTexto(String query) {
        String conteudo = (String) texto.getText();
        if(conteudo.contains(query))
        {
            Toast.makeText(getBaseContext(), " " + query, Toast.LENGTH_SHORT).show();
            // texto.setTextKeepState(query);
        }
        else
        {
            Toast.makeText(getBaseContext(), "Palavra não localizada!", Toast.LENGTH_SHORT).show();
        }
    }

    public void abriSite(View botaoView)
    {

        Intent intecao = new Intent(Intent.ACTION_VIEW, Uri.parse("https://appengine.google.com"));
        startActivity(intecao);
    }
}