package br.com.unitins.cloudcomputing;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.content.Intent.*;

public class Tela_ibm extends AppCompatActivity {

    TextView texto = null;
    SearchView sv = null;

    String resultado = "";
    Persistencia persistencia;

    Button webButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_ibm);

        sv = (SearchView) findViewById(R.id.searchView);
        sv.setOnQueryTextListener(new SearchFilter());

        texto = (TextView) findViewById(R.id.textConteudo);
        carregar();

        webButton = (Button) findViewById(R.id.webButton);


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

        persistencia.gravaArquivo("ibm.txt", this, "O que é IBM Bluemix?\n" +
                "Implementação da arquitetura de nuvem aberta da IBM baseada no projeto Cloud Foundry\n" +
                "IBM® Bluemix é a oferta de nuvem mais recente da IBM. IBM Bluemix é uma oferta de plataforma como serviço (PaaS) baseada em um projeto de código aberto de Cloud Foundry que promete oferecer funções e serviços em nível empresarial fáceis de integrar aos aplicativos da nuvem. Este documento proporciona uma visão geral do IBM Bluemix e Cloud Foundry e a diferença entre ambos. Também enumera os serviços de IBM Bluemix.\n" +
                "0 Comentários\n" +
                "Angel Tomala-Reyes, Senior Software Engineer, IBM\n" +
                "06/Mai/2014\n" +
                "expand\n" +
                "Índice\n" +
                "Angel Tomala-Reyes em Cloud Foundry e IBM Bluemix\n" +
                "Video: Angel Tomala-Reyes em Cloud Foundry e IBM Bluemix\n" +
                "Transcricao\n" +
                "Introdução\n" +
                "IBM Bluemix é a oferta de nuvem mais recente da IBM. Permite que as organizações e os desenvolvedores criem, implementem e gerenciem aplicativos na nuvem de maneira fácil e rápida. O Bluemix é uma implementação da Arquitetura de Nuvem Aberta da IBM baseada em Cloud Foundry, uma plataforma como serviço (PaaS) de código aberto. O Bluemix oferece serviços em nível empresarial que podem ser facilmente integrados aos seus aplicativos de nuvem sem que seja necessário saber como instalá-los ou configurá-los. Este artigo oferece uma descrição de alto nível sobre o Cloud Foundry e o IBM Bluemix e define as características e os serviços que fazem parte do Bluemix e que o tornam uma plataforma como serviço muito atraente para o mercado atual.\n" +
                "O que é Cloud Foundry?\n" +
                "Cloud Foundry é uma plataforma como serviço (PaaS) de código aberto que permite criar e implementar aplicativos rapidamente na nuvem. Devido às suas raízes de código aberto, o Cloud Foundry não é específico para o provedor e não o limita a softwares de propriedade intelectual ou infraestrutura de nuvem. O Cloud Foundry extrai a infraestrutura implícita da nuvem para operar a nuvem, permitindo se concentrar no desenvolvimento de aplicativos da nuvem. O melhor do Cloud Foundry é que permite escolher. Os desenvolvedores e as organizações podem escolher:\n" +
                "Marcos de Desenvolvimento: O Cloud Foundry oferece suporte ao código Java™, Spring, Ruby, Node.js e marcos personalizados.\n" +
                "Serviços de Aplicativos: O Cloud Foundry oferece suporte para MySQL, MongoDB, PostgreSQL, Redis, RabbitMQ e serviços feitos sob medida.\n" +
                "Nuvens: Os desenvolvedores e as organizações podem escolher entre operar o Cloud Foundry em nuvens Públicas, Privadas, VMWare e baseadas em OpenStack.\n" +
                "A capacidade do Cloud Foundry oferecer possibilidade de escolha é dada por meio dos buildpacks, uma forma conveniente de empacotamento de frameworks e tempos de execução. Os buildpacks podem estar baseados na comunidade, serem desenvolvidos sob medida ou do zero. Em outras palavras, se você não encontrar um framework ou um buildpack de serviço que se adapte às suas necessidades, pode modificar um buildpack existente ou criar o seu próprio. Na utilização de buildpacks, as empresas podem oferecer serviços em nível empresarial como a oferta de nuvem do Bluemix.\n" +
                "O que é IBM Bluemix?\n" +
                "IBM Bluemix é uma implementação da Arquitectura de Nuvem Aberta da IBM, baseada em Cloud Foundry, que permite criar, implementar e gerenciar rapidamente seus aplicativos na nuvem. Como o Bluemix está baseado em Cloud Foundry, é possível aproveitar o ecossistema de frameworks e serviços de tempo de execução em crescimento. Além de proporcionar frameworks e serviços adicionais, o Bluemix fornece um painel para que você crie, visualize e gerencie seus aplicativos e serviços, bem como monitorar o uso dos recursos do seu aplicativo. O painel do Bluemix também oferece a possibilidade de gerenciar organizações, espaços e acesso do usuário.\n" +
                "O Bluemix oferece acesso aos serviços entregues por meio do Cloud Foundry e melhora a coleção de serviços com os seguintes serviços da IBM:");

        resultado = persistencia.lerArquivo("ibm.txt", this);
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

        Intent intecao = new Intent(Intent.ACTION_VIEW, Uri.parse("https://console.ng.bluemix.net/"));
        startActivity(intecao);
    }
}