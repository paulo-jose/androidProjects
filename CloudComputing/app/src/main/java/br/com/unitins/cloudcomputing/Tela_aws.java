package br.com.unitins.cloudcomputing;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Tela_aws extends AppCompatActivity {

    TextView texto = null;
    SearchView sv = null;
    String resultado = "";
    Persistencia persistencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_aws);

        sv = (SearchView) findViewById(R.id.searchView);
        sv.setOnQueryTextListener(new SearchFilter());

        texto = (TextView) findViewById(R.id.textConteudo);


        carregar();


        texto.setText(resultado);


    }

    private void carregar() {

        persistencia.gravaArquivo("aws.txt", this, "AWS começou a oferecer sua plataforma de infraestrutura de tecnologia em 2006. Ela possui dezenas de serviços, cada qual expõe uma área de funcionamento. Enquanto a variedade de serviços oferece flexibilidade para como você deseja gerenciar sua infraestrutura AWS, ele pode ser um desafio para descobrir quais serviços utilizar e quais realmente supri as necessidades de cada um.\n" +
                "Na Amazon AWS podemos escalar os recursos conforme a demanda, e também ajustar dinamicamente a quantidade de servidores que ficam atendendo ás requisições. Um exemplo prático disso seria definir uma quantidade mínima de servidores que ficaram disponíveis para anteder as requisições, e durante os horários de pico, serviços da AWS permitem monitorar esses servidor,  e caso ele esteja sobrecarregado, automaticamente novos servidores podem ser adicionados para atender às requisições (LECHETA, 2014)\n" +
                "AWS Elastic Beanstalk, tem a capacidade de implantar rapidamente e gerenciar aplicativos na nuvem AWS sem se preocupar com a infraestrutura que executa essas aplicações. AWS Elastic Beantalk reduz a complexidade de gerenciamento. Para a utilização basta fazer upload da aplicação e Elastic Beanstalk automaticamente gerencia os detalhes de provisionamento de capacidade, balanceamento de carga, dimensionamento e monitoramento automático para que a aplicação rode sem problemas. \n" +
                "AWS Elastic Beanstalk, não cobra por utilização da solução, apenas paga pelos recursos AWS necessários para armazenamento e execução do aplicativos. Esta solução garanti maior flexibilidade para os desenvolvedores, pois o sistema poderá ser desenvolvido em qualquer computador por meio de uma IDE (ex: Eclipse). O Elastic Beanstalk fornece suporte as linguagens PHP, Java, Python, Ruby, Node.js, .NET ou Docker. (AMAZON, 2016).\n" +
                "No Elastic Beanstalk é suportado várias pilhas de software famosos no mercado, como o Apache HTTP Server para Node.js, PHP e Python, o Passenger para Ryby, o IIS para .NET e o Apache Tomcat para Java. Existem  outras vantagens em utilizar o Elastic Beanstalk, como facilidade para fazer deploy, controle de versões, balanceador de carga com escolamento automático e configurável, monitoramento de instancias com a CloudWatch e acesso ao EC2 para configuração dos recursos computacionais necessário para sua aplicação (LACHETA, 2014).\n" +
                "atuando nos bastidores para entregar as informações que os torna tão úteis.  A AWS hospeda sites móveis e back-ends móveis, para que você possa manter o foco no seu aplicativo.");

        resultado = persistencia.lerArquivo("aws.txt", this);
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

        Intent intecao = new Intent(Intent.ACTION_VIEW, Uri.parse("https://aws.amazon.com/pt/documentation/elastic-beanstalk"));
        startActivity(intecao);
    }
}
