package br.com.unitins.androidbd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;


public class GerenciadorBanco extends SQLiteOpenHelper
{
    String[] scriptCriaBanco = {"create table carro (_id integer primary key autoincrement,nome text not null,placa text not null,ano text not null);"};

    public final String scriptApagaBD = "DROP TABLE IF EXISTS carro";
    Context vrContexto = null;

    GerenciadorBanco(Context contexto, String nome, int versao)
    {
        super(contexto, nome, null, versao);
        vrContexto = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        for (int iIndex=0; iIndex < scriptCriaBanco.length; iIndex++)
        {
            db.execSQL(scriptCriaBanco[iIndex]);
        }
    }

    public void insereDados(ContentValues dados)
    {
        SQLiteDatabase bancoDados = this.getWritableDatabase();

        bancoDados.insert("carro", null, dados);

        Toast.makeText(vrContexto, "Insercao realizada", Toast.LENGTH_SHORT).show();
    }

    public void removerCarro(String placa)
    {
        SQLiteDatabase bancoDados = this.getReadableDatabase();
        bancoDados.delete("carro", "placa = "+"'"+placa+"'" , null);
        

        bancoDados.close();
        Toast.makeText(vrContexto, "Remoção realizada com sucesso", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<String> listaCarros()
    {
        ArrayList<String> lista = new ArrayList<String>();
        SQLiteDatabase bancoDados = this.getReadableDatabase();
        Cursor cursor = bancoDados.query("carro", new String[]{"nome", "placa", "ano"}, null, null, null, null, null);

        while(cursor.moveToNext())
        {
            lista.add(cursor.getString(0));
            lista.add(cursor.getString(1));
            lista.add(cursor.getString(2));
        }

        return lista;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(scriptApagaBD);
    }


}
