package br.com.unitins.broadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Paulo on 04/05/2016.
 */
public class ReceiverViaXML extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {
        TelaPrinciapl.vrImagem.setImageResource(R.drawable.verde);
    }

}

