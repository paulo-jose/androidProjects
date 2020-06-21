package br.com.unitins.video;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class TelaInicial extends AppCompatActivity {

    private VideoView vrVideo = null;
    Uri video = Uri.parse("android.resource://br.com.unitins.video/raw/video");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_inicial);

        vrVideo = (VideoView)findViewById(R.id.videoView);
        vrVideo.setMediaController(new MediaController(this));
        vrVideo.setVideoURI(video);
    }

    public void stop(View view)
    {
        vrVideo.stopPlayback();
        vrVideo.setVideoURI(video);
    }

    public void pause(View view)
    {
        vrVideo.pause();
    }

    public void play(View view)
    {
        vrVideo.start();
    }

    public void nativo(View view)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("android.resource://br.com.unitins.video/raw/video"));
        startActivity(intent);
    }
}
