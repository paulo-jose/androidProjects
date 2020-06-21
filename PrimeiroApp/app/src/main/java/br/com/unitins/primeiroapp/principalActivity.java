package br.com.unitins.primeiroapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class principalActivity extends AppCompatActivity {

    Button meuBotao = null;
    Button btCalcular = null;
    TextView minhaLabel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        if (savedInstanceState != null) {
            Log.i("info", "valor salvo" + savedInstanceState.getInt("chave"));
        }

        btCalcular = (Button) findViewById(R.id.btCalcular_Tela1);
        minhaLabel = (TextView) findViewById(R.id.textView);

        meuBotao = new Button(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        meuBotao.setText("criando botao");
        this.addContentView(meuBotao, params);

    }

    public void trataBotao(View botao)
    {
        if(botao == meuBotao || botao.getId() == R.id.btCalcular_Tela1)
        {
            minhaLabel.setText("Alterou");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    //MEtodo chamado antes de destruir a aplicação
    public void onSaveInstanceState(Bundle saveInstancia)
    {
        saveInstancia.putInt("chave", 263);
    }

    protected void onPause()
    {
        super.onPause();
        Log.i("info", "Pausando ...");
    }

    protected void onStop()
    {
        super.onStop();
        Log.i("info", "stop ...");
    }

    protected void onStart()
    {
        super.onStart();
        Log.i("info", "Iniciando ...");
    }

    protected void onRestart()
    {
        super.onRestart();
        Log.i("info", "Reiniciando ...");
    }

    protected void onDestroy()
    {
        super.onPause();
        Log.i("info", "Destruindo ...");
        meuBotao = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
