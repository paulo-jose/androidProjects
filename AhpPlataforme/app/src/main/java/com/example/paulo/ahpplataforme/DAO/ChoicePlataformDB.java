package com.example.paulo.ahpplataforme.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PAULO on 18/09/2016.
 */
public class ChoicePlataformDB extends SQLiteOpenHelper {

    private static final  String NOME_DB = "plataforma";
    private static final  int VERSAO_DB = 5;


    public ChoicePlataformDB(Context context)
    {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {

        bd.execSQL("create table alternativa(_id integer primary key autoincrement, nome text not null);");
        bd.execSQL("create table criterio(_id integer primary key autoincrement, nome text not null);");
        bd.execSQL("create table criterio_alternativa(_id integer primary key autoincrement, id_alternativa integer not null, id_criterio integer not null, peso text not null);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL("drop table alternativa;");
        bd.execSQL("drop table criterio;");
        bd.execSQL("drop table criterio_alternativa;");
        onCreate(bd);

    }
}
