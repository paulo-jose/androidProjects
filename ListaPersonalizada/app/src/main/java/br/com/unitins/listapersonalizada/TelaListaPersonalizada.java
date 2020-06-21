package br.com.unitins.listapersonalizada;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


class ModeloCelula
{
    int tipoIcon;
    String nomeUsuario;
    String rotuloIcon;

    public ModeloCelula(String nomeUsuario, String rotuloIcon, int tipoIcon) {
        this.nomeUsuario = nomeUsuario;
        this.rotuloIcon = rotuloIcon;
        this.tipoIcon = tipoIcon;
    }

    public ModeloCelula() {
        this.tipoIcon = 0;
        this.nomeUsuario = "";
        this.rotuloIcon = "";
    }
}

class MeuAdaptador extends ArrayAdapter<ModeloCelula>
{

    Context vrContext;

    public MeuAdaptador(Context context, List<ModeloCelula> vetorDados) {

        super(context, 0, vetorDados);
        vrContext = context;
    }

    Bitmap retornaImagem(int codigo)
    {
        if(codigo == 0)
        {
            return BitmapFactory.decodeResource(vrContext.getResources(), R.drawable.feliz);
        }
        else if(codigo == 1)
        {
            return BitmapFactory.decodeResource(vrContext.getResources(), R.drawable.preocupado);
        }
        else if(codigo == 2)
        {
            return BitmapFactory.decodeResource(vrContext.getResources(), R.drawable.bravo);
        }
        return null;
    }

    public View getView(int indice, View celularReciclada, ViewGroup layouPai){

        ModeloCelula usuario = this.getItem(indice);

        if(celularReciclada == null)
        {
            Log.i("info", "celula criada");
          celularReciclada = LayoutInflater.from(getContext()).inflate(R.layout.layout_celular, layouPai, false);

        }
        else
        {
            Log.i("info", "celular reciclada");
        }

        TextView nome = (TextView)celularReciclada.findViewById(R.id.nome_usuario);
        TextView rotulo = (TextView)celularReciclada.findViewById(R.id.rotuloIcone);
        ImageView icone = (ImageView)celularReciclada.findViewById(R.id.iconeUsuario);

        nome.setText(usuario.nomeUsuario);
        rotulo.setText(usuario.rotuloIcon);
        icone.setImageBitmap(retornaImagem(usuario.tipoIcon));



        return celularReciclada;

    }
}

public class TelaListaPersonalizada extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<ModeloCelula> vetorUsuario = null;
    MeuAdaptador adaptador = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_lista_personalizada);

     //   Gera o array do objetos que sera utilizado pelo adapter para popular o LisView
        vetorUsuario = new ArrayList<ModeloCelula>();
        vetorUsuario.add(new ModeloCelula( "Silvano", "Feliz", 0));
        vetorUsuario.add(new ModeloCelula( "Denis", "Preocupado", 1));
        vetorUsuario.add(new ModeloCelula( "Paulo", "Raiva", 2));


       salvaDados();

        //Criar o objeto Adaptar
        adaptador = new MeuAdaptador(this, vetorUsuario);
        ListView meuList = (ListView)findViewById(R.id.listaUsuarios);
        meuList.setAdapter(adaptador);
        meuList.setOnItemClickListener(this);

        SharedPreferences vrShared = getSharedPreferences("TOTAL_LISTA", MODE_PRIVATE);

        if(vrShared.contains("total"))
        {
            Log.i("info", vrShared.getString("total", "###"));
        }

    }

    public void salvaDados()
    {
        String linha = "";
        FileWriter vrFile = null;
        try {
            vrFile = new FileWriter(this.getFilesDir().getPath()+"/dados.txt", false);

            for(ModeloCelula aux : vetorUsuario)
            {
                vrFile.write(aux.nomeUsuario+"\n");
                vrFile.write(aux.rotuloIcon+"\n");
                vrFile.write(aux.tipoIcon+"\n");
            }

            vrFile.flush();
            vrFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LerDados()
    {

        try {
            String linha = "";
            FileReader vrFile = new FileReader(this.getFilesDir().getPath()+"/dados.txt");
            BufferedReader vrBuffer = new BufferedReader(vrFile);
            ModeloCelula aux = new ModeloCelula();

            while((linha = vrBuffer.readLine())!= null)
            {

                aux.nomeUsuario = linha;
                linha = vrBuffer.readLine();
                aux.rotuloIcon = linha;
                linha = vrBuffer.readLine();
                aux.tipoIcon = Integer.parseInt(linha);
                vetorUsuario.add(aux);
            }

            vrFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onDestroy()
    {
        super.onDestroy();
        SharedPreferences vrShared = getSharedPreferences("TOTAL_LIST", MODE_PRIVATE);
        SharedPreferences.Editor editor = vrShared.edit();
        editor.putString("total", vetorUsuario.size() +"");
        editor.commit();
        salvaDados();
    }

    public void onItemClick(AdapterView<?> adapter, View celula, int posicao, long id)
    {
        ModeloCelula dados = vetorUsuario.get(posicao);

        Log.i("info", dados.nomeUsuario + " clicado");
    }

    public void trataInserir(View botao)
    {
        Random sorteio = new Random();
        String[] nomes = {"Silvano", "Denis", "Paulo", "Cristian"};
        String[] estados = {"Feliz", "Preocupado", "Raiva"};

        int numSorteado = sorteio.nextInt(4);
        int estadoSorteado = sorteio.nextInt(3);

        ModeloCelula vrModelo = new ModeloCelula(nomes[numSorteado], estados[estadoSorteado], estadoSorteado);

        vetorUsuario.add(vrModelo);
        salvaDados();
        adaptador.notifyDataSetChanged();

    }

    public void trataDeletar(View botao)
    {
        vetorUsuario.remove(0);
        adaptador.notifyDataSetChanged();
    }
}
