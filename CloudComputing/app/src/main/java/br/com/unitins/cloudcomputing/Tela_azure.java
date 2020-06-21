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
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.*;

public class Tela_azure extends AppCompatActivity {

    TextView texto = null;
    SearchView sv = null;
    String resultado = "";
    Persistencia persistencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_azure);
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

        persistencia.gravaArquivo("azure.txt", this, "Funcionamento[editar | editar código-fonte]\n" +
                "Sua computação em nuvem é definida como uma combinação de software como serviço[3] (SaaS) com computação em grelha.\n" +
                "A computação em grelha dá o poder de computação e alta escalabilidade oferecida para as aplicações, através de milhares de máquinas (hardware) disponíveis em centros de processamento de dados de última geração. De software como serviço se tem a capacidade de contratar um serviço e pagar somente pelo uso, permitindo a redução de custos operacionais, com uma configuração de infraestrutura realmente mais aderente às necessidades[carece de fontes].\n" +
                "Recursos\n" +
                "Além dos recursos de computação, armazenamento e administração oferecidos pelo Microsoft Azure, a plataforma também disponibiliza uma série de serviços para a construção de aplicações distribuídas, além da total integração com a solução on-premise (local) baseada em plataforma .NET. Entre os principais serviços da plataforma Windows Azure há o SQL Azure Database, Azure AppFabric Platform e uma API de gerenciamento e monitoração para aplicações colocadas na nuvem.\n" +
                "Distribuição\n" +
                "O Microsoft Azure entrou em produção em 1 de Janeiro de 2010 e sua fase comercial está no ar desde 1 de Fevereiro de 2010. Na primeira onda de lançamentos, 21 países foram atendidos.\n" +
                "Até meados de março de 2010, os seguintes datacenters estavam disponíveis para deployment de solução sobre o Microsoft Azure:\n" +
                "Europe: West Europe e North Europe\n" +
                "Asia Pacific: East Asia e Southeast Asia\n" +
                "North America: South Central US, North Central US, East US, West US.");

        resultado = persistencia.lerArquivo("azure.txt", this);
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

        Intent intecao = new Intent(Intent.ACTION_VIEW, Uri.parse("https://azure.microsoft.com/pt-br"));
        startActivity(intecao);
    }
}