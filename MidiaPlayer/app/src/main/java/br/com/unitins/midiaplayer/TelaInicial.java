package br.com.unitins.midiaplayer;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class TelaInicial extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private MediaPlayer vrTocador = null;
    private SeekBar vrBarra = null;
    private AssetFileDescriptor descriptor = null;
    private TextView minutos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_inicial);
        minutos = (TextView)findViewById(R.id.textViewMinuto);
        vrBarra = (SeekBar)findViewById(R.id.seekBar);

        vrBarra.setOnSeekBarChangeListener(this);


        vrTocador = new MediaPlayer();
        vrTocador.create(this, R.raw.musica);

        try
        {
            descriptor = getResources().openRawResourceFd(R.raw.musica);
            vrTocador.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            vrTocador.prepare();

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }



    public void play(View view)
    {

        vrTocador.start();
        //vrBarra.setProgress(0);
        vrBarra.setMax(vrTocador.getDuration());



        Thread thread = new Thread()
        {
            public void run()
            {
                while (vrTocador.isPlaying())
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           long segundos = (vrTocador.getCurrentPosition()/1000) % 60;
                           long vrminutos = (vrTocador.getCurrentPosition()/60000) % 60;
                           long horas = (vrTocador.getCurrentPosition()/360000) % 24;


                            vrBarra.setProgress((int) (vrTocador.getCurrentPosition()));
                            minutos.setText(String.format("%02d:%02d:%02d", horas, vrminutos, segundos) );
                        }
                    });
                }

            }
        };

        thread.start();

    }

    public void stop(View view)
    {

        try
        {

            vrTocador.stop();
            vrTocador.reset();
            vrTocador.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            vrTocador.prepare();

            vrBarra.setProgress(0);
            minutos.setText(""+ 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pause(View view)
    {
        vrTocador.pause();

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser)
        {
            vrTocador.seekTo(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
