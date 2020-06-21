package com.example.paulo.ahpplataforme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.paulo.ahpplataforme.model.CriterioViewHolder;
import com.example.paulo.ahpplataforme.ParCriterios;
import com.example.paulo.ahpplataforme.R;

import java.util.List;

/**
 * Created by PAULO on 06/07/2016.
 */
public class Adapter extends ArrayAdapter<ParCriterios>{

    TextView nomeCriterio1 = null;
    TextView nomeCriterio2 = null;

    RadioGroup rgb1 = null;
    RadioGroup rgb2 = null;

    RadioButton rdb1 = null;
    RadioButton rdb2 = null;
    RadioButton rdb3 = null;
    RadioButton rdb4 = null;
    RadioButton rdb5 = null;
    RadioButton rdb6 = null;
    RadioButton rdb7 = null;
    RadioButton rdb8 = null;
    RadioButton rdb9 = null;
    RadioButton rdb10 = null;

    int posicao = -1;

    Context context;

    public Adapter(Context context, List<ParCriterios> criterios) {
        super(context, 0, criterios);
        this.context = context;
    }

    View view;

    public View getView(final int indice, View celularReciclada, ViewGroup layouPai){


        final CriterioViewHolder holder;
        final ParCriterios criterio = this.getItem(indice);


        celularReciclada = LayoutInflater.from(getContext()).inflate(R.layout.layout_celula, layouPai, false);


        nomeCriterio1 = (TextView) celularReciclada.findViewById(R.id.textViewCriterioI);
        nomeCriterio2 = (TextView) celularReciclada.findViewById(R.id.textViewCriterioII);

        rgb1 = (RadioGroup) celularReciclada.findViewById(R.id.radioGroup);
        rgb2 = (RadioGroup) celularReciclada.findViewById(R.id.radioGroup2);


        selecionar(criterio);

        nomeCriterio1.setText(criterio.getNomeCriterio1());
        nomeCriterio2.setText(criterio.getNomeCriterio2());


        holder = new CriterioViewHolder(celularReciclada);
        celularReciclada.setTag(holder);




        holder.getRgb1().setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(group.getCheckedRadioButtonId() == R.id.radioButton11)
                {
                    criterio.setPesoCriterio1(1);
                    holder.getRgb2().clearCheck();

                }
                else if(group.getCheckedRadioButtonId() == R.id.radioButton12)
                {
                    criterio.setPesoCriterio1(3);
                    holder.getRgb2().clearCheck();

                }
                else if (group.getCheckedRadioButtonId() == R.id.radioButton13)
                {
                    criterio.setPesoCriterio1(5);
                    holder.getRgb2().clearCheck();

                }
                else if (group.getCheckedRadioButtonId() == R.id.radioButton14)
                {
                    criterio.setPesoCriterio1(7);
                    holder.getRgb2().clearCheck();
                }
                else if (group.getCheckedRadioButtonId() == R.id.radioButton15)
                {
                    criterio.setPesoCriterio1(9);
                    holder.getRgb2().clearCheck();
                }
            }
        });


        holder.getRgb2().setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(group.getCheckedRadioButtonId() == R.id.radioButton16)
                {
                    criterio.setPesoCriterio2(1);
                    holder.getRgb1().clearCheck();
                }
                else if(group.getCheckedRadioButtonId() == R.id.radioButton17)
                {
                    criterio.setPesoCriterio2(3);
                    holder.getRgb1().clearCheck();

                }
                else if (group.getCheckedRadioButtonId() == R.id.radioButton18)
                {
                    criterio.setPesoCriterio2(5);
                    holder.getRgb1().clearCheck();

                }
                else if (group.getCheckedRadioButtonId() == R.id.radioButton19)
                {
                    criterio.setPesoCriterio2(7);
                    holder.getRgb1().clearCheck();

                }
                else if (group.getCheckedRadioButtonId() == R.id.radioButton20)
                {
                    criterio.setPesoCriterio2(9);
                    holder.getRgb1().clearCheck();
                }

            }
        });


        return celularReciclada;

    }


    private void selecionar(ParCriterios criterio) {

        if(criterio.getPesoCriterio1() == 1.0)
        {
            rgb1.check(R.id.radioButton11);

        }
        else if (criterio.getPesoCriterio1() == 3.0)
        {
            rgb1.check(R.id.radioButton12);

        }
        else if (criterio.getPesoCriterio1() == 5.0)
        {
            rgb1.check(R.id.radioButton13);

        }
        else if (criterio.getPesoCriterio1() == 7.0)
        {
            rgb1.check(R.id.radioButton14);
        }
        else if (criterio.getPesoCriterio1() == 9.0)
        {
            rgb1.check(R.id.radioButton15);
        }
        else if (criterio.getPesoCriterio2() == 1.0)
        {
            rgb2.check(R.id.radioButton16);
        }
        else if (criterio.getPesoCriterio2() == 3.0)
        {
            rgb2.check(R.id.radioButton17);
        }
        else if (criterio.getPesoCriterio2() == 5.0)
        {
            rgb2.check(R.id.radioButton18);
        }
        else if (criterio.getPesoCriterio2() == 7.0)
        {
            rgb2.check(R.id.radioButton19);
        }
        else if (criterio.getPesoCriterio2() == 9.0)
        {
            rgb2.check(R.id.radioButton20);
        }


    }



}
