package com.example.paulo.ahpplataforme.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.paulo.ahpplataforme.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class TelaGrafico extends AppCompatActivity {

    protected static List<Double> resultados;
    private BarChart barra;
    private Button botao;
    private String vencedor = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_grafico);

        barra = (BarChart) findViewById(R.id.grafico);
        botao = (Button) findViewById(R.id.buttonIrSite);

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        ArrayList<BarDataSet> barDataSet = new ArrayList<>();

        for (int i = 0; i < resultados.size(); i ++)
        {
            String w = String.valueOf(resultados.get(i));
            barEntries.add(new BarEntry(Float.parseFloat(w), i));
            System.out.println("Resuldados: " +  w);

        }

        String[] alternativas = {"AWS", "GAE", "MS AZURE", "IBM BLUMIX"};

        String[] sites = {  "https://aws.amazon.com/pt/",
                            "https://cloud.google.com/appengine/",
                            "https://azure.microsoft.com/pt-br/?wt.mc_id=AID_sem_IPuEe0j2&",
                            "https://console.ng.bluemix.net/"};

        Double max = 0.0;
        for (int i = 0; i < resultados.size(); i ++) {

            if (max < resultados.get(i)) {
                vencedor = sites[i];
                max = resultados.get(i);
            }
        }


        BarDataSet set = new BarDataSet(barEntries, ""+alternativas[0]+", "+alternativas[1]+", "+alternativas[2]+", "+alternativas[3]+"" );

        BarData barData = new BarData(alternativas, set);
        set.setColors(new int[]{R.color.colorGold , R.color.colorRed, R.color.colorPrimary, R.color.colorDarkGreen  } , this);
        set.setStackLabels(alternativas);
        set.setBarSpacePercent(10);
        barDataSet.add(set);

        barra.setData(barData);
        barra.setStartAtZero(false);
        barra.setTouchEnabled(true);
        barra.setDragEnabled(true);
        barra.setScaleEnabled(true);


    }

    public void ir(View v)
    {
        Intent intecao = new Intent(Intent.ACTION_VIEW, Uri.parse(vencedor));
        startActivity(intecao);
    }
}
