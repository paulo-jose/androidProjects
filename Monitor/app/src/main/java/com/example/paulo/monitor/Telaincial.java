package com.example.paulo.monitor;

import android.app.ActivityManager;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Telaincial extends AppCompatActivity {


    TextView tvCpu = null;
    TextView tvRam = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_telaincial);

        tvCpu = (TextView)findViewById(R.id.tvCPU);
        tvRam = (TextView)findViewById(R.id.tvCPU);

    }

    public void getCpuInfo() {
        try {
            Process proc = Runtime.getRuntime().exec("cat /proc/cpuinfo");
            InputStream is = proc.getInputStream();

            tvCpu.setText(getStringFromInputStream(is));
        }
        catch (IOException e) {
            Log.e("info", "------ getCpuInfo " + e.getMessage());
        }
    }

    public void getMemoryInfo() {
        try {
            Process proc = Runtime.getRuntime().exec("cat /proc/meminfo");
            InputStream is = proc.getInputStream();
            tvRam.setText(getStringFromInputStream(is));
        }
        catch (IOException e) {
            Log.e("info", "------ getMemoryInfo " + e.getMessage());
        }
    }

    private static String getStringFromInputStream(InputStream is) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;

        try {
            while((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        }
        catch (IOException e) {
            Log.e("info", "------ getStringFromInputStream " + e.getMessage());
        }
        finally {
            if(br != null) {
                try {
                    br.close();
                }
                catch (IOException e) {
                    Log.e("info", "------ getStringFromInputStream " + e.getMessage());
                }
            }
        }

        return sb.toString();
    }

    public void iniciar(View view)
    {
        getCpuInfo();
        //getMemoryInfo();

    }


}
