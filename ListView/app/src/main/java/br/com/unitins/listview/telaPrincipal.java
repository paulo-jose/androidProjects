package br.com.unitins.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Comparator;

public class telaPrincipal extends AppCompatActivity {


    public String capitais[] = {"Manaus", "Maceio", "Fortaleza", "Salvador", "Palmas"};

    public ListView vrList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_principal);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, capitais);

        adapter.sort(new Comparodor());

        vrList = (ListView)findViewById(R.id.listView);
        vrList.setAdapter(adapter);
    }



}

class Comparodor implements Comparator<String>
{
    @Override
    public int compare(String nome1, String nome2) {
        return nome1.compareTo(nome1);
    }
}
