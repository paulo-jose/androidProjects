package com.example.paulo.ahpplataforme.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.paulo.ahpplataforme.R;

/**
 * Created by PAULO on 13/09/2016.
 */
public class CriterioViewHolder extends RecyclerView.ViewHolder {

    private RadioGroup rgb1;
    private RadioGroup rgb2;
    private TextView nomeCriterio1;
    private TextView nomeCriterio2;


    public CriterioViewHolder(View itemView) {
        super(itemView);
        rgb1 = (RadioGroup) itemView.findViewById(R.id.radioGroup);
        rgb2 = (RadioGroup) itemView.findViewById(R.id.radioGroup2);
        nomeCriterio1 = (TextView) itemView.findViewById(R.id.textViewCriterioI);
        nomeCriterio1 = (TextView) itemView.findViewById(R.id.textViewCriterioII);

    }

    public RadioGroup getRgb1() {
        return rgb1;
    }

    public void setRgb1(RadioGroup rgb1) {
        this.rgb1 = rgb1;
    }

    public RadioGroup getRgb2() {
        return rgb2;
    }

    public void setRgb2(RadioGroup rgb2) {
        this.rgb2 = rgb2;
    }

    public TextView getNomeCriterio1() {
        return nomeCriterio1;
    }

    public void setNomeCriterio1(TextView nomeCriterio1) {
        this.nomeCriterio1 = nomeCriterio1;
    }

    public TextView getNomeCriterio2() {
        return nomeCriterio2;
    }

    public void setNomeCriterio2(TextView nomeCriterio2) {
        this.nomeCriterio2 = nomeCriterio2;
    }
}
