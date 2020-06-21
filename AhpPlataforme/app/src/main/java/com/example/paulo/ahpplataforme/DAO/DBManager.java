package com.example.paulo.ahpplataforme.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.paulo.ahpplataforme.model.Alternativa;
import com.example.paulo.ahpplataforme.model.AlternativaCriterio;
import com.example.paulo.ahpplataforme.model.Criterio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PAULO on 18/09/2016.
 */
public class DBManager {

    private SQLiteDatabase bd;

    public DBManager(Context context)
    {
        ChoicePlataformDB auxBd = new ChoicePlataformDB(context);
        bd = auxBd.getWritableDatabase();
    }

    public void inserir(Alternativa alternativa)
    {
        ContentValues valores = new ContentValues();
        valores.put("nome", alternativa.getNome());

        bd.insert("alternativa", null, valores);
    }

    public void inserir(Criterio criterio)
    {
        ContentValues valores = new ContentValues();
        valores.put("nome", criterio.getNome());

        bd.insert("criterio", null, valores);
    }

    public long inserir(AlternativaCriterio alternativaCriterio)
    {
        ContentValues valores = new ContentValues();
        valores.put("id_alternativa", alternativaCriterio.getAlternativa().getId());
        valores.put("id_criterio", alternativaCriterio.getCriterio().getId());
        valores.put("peso", alternativaCriterio.getPeso());

        return bd.insert("criterio_alternativa", null, valores);
    }

    public void inserir(List<Alternativa> lista)
    {
        for(Alternativa alternativa: lista)
        {
            ContentValues valores = new ContentValues();
            valores.put("nome", alternativa.getNome());

            bd.insert("alternativa", null, valores);
        }
    }

    public void altualizar(Alternativa alternativa)
    {
        ContentValues valores = new ContentValues();
        valores.put("nome", alternativa.getNome());

        bd.update("alternativa", valores, "_id=?", new String[]{String.valueOf(alternativa.getId())});
    }

    public void altualizar(Criterio criterio)
    {
        ContentValues valores = new ContentValues();
        valores.put("nome", criterio.getNome());

        bd.update("criterio", valores, "_id=?", new String[]{String.valueOf(criterio.getId())});
    }

    public void altualizar(AlternativaCriterio alternativaCriterio)
    {
        ContentValues valores = new ContentValues();
        valores.put("peso", alternativaCriterio.getPeso());
        bd.update("criterio_alternativa", valores, "_id=?", new String[]{String.valueOf(alternativaCriterio.getId())});
    }

    public void deletar(Alternativa alternativa)
    {
        bd.delete("alternativa", "_id = "+alternativa.getId(), null);
    }

    public void deletar(Criterio criterio)
    {
        bd.delete("criterio", "_id = "+criterio.getId(), null);
    }

    public void deletar(AlternativaCriterio alternativaCriterio)
    {
        bd.delete("criterio_alternativa", "_id = "+alternativaCriterio.getId(), null);
    }

    public void deletarAlternativaCriterio(Alternativa alternativa)
    {
        bd.delete("criterio_alternativa", "id_alternativa = "+alternativa.getId(), null);
    }

    public void deletarAlternativaCriterio(Criterio criterio)
    {
        Criterio aux = null;
        Cursor cursor = bd.query("criterio_alternativa", new String[]{"_id, id_alternativa, id_criterio"}, "id_criterio = " +criterio.getId(), null, null, null, null, null );
        if(cursor.getCount() >0)
        {
            cursor.moveToNext();
            aux = new Criterio();
            do {

                aux.setId(cursor.getInt(2));
                if(aux.getId() == criterio.getId())
                    bd.delete("criterio_alternativa", "id_criterio = "+criterio.getId(), null);

            }while (cursor.moveToNext());
        }


    }

    public void deletarTodos()
    {
        bd.delete("criterio_alternativa", "1", null);
    }


    public Alternativa buscaAlternativaPorId(int id)
    {
        Alternativa alternativa = null;
        String[] colunas = new String[]{"_id", "nome"};
        String where = "_id" + "=" + id;
        Cursor cursor = bd.query("alternativa", colunas, where, null, null, null, "nome ASC");

        if(cursor.getCount() > 0)
        {
            cursor.moveToNext();
            alternativa = new Alternativa();
            do
            {
                alternativa.setId(cursor.getInt(0));
                alternativa.setNome(cursor.getString(1));

            }while (cursor.moveToNext());
        }

        return  alternativa;
    }



    public List<Alternativa> buscar()
    {
        List<Alternativa> listAlternativa = new ArrayList<>();
        String[] colunas = new String[]{"_id", "nome"};

        Cursor cursor = bd.query("alternativa", colunas, null, null, null, null, "_id ASC");

        if(cursor.getCount() > 0)
        {
            cursor.moveToNext();
            do
            {
                Alternativa alternativa = new Alternativa();
                alternativa.setId(cursor.getInt(0));
                alternativa.setNome(cursor.getString(1));
                listAlternativa.add(alternativa);

            }while (cursor.moveToNext());
        }

        return listAlternativa;
    }


    public Criterio bsucarCriterioPorId(int id)
    {
        Criterio criterio = null;
        String[] colunas = new String[]{"_id", "nome"};
        String where = "_id" + "=" + id;
        Cursor cursor = bd.query("criterio", colunas, where, null, null, null, "nome ASC");

        if(cursor.getCount() > 0)
        {
            cursor.moveToNext();
            do
            {
                criterio = new Criterio();
                criterio.setId(cursor.getInt(0));
                criterio.setNome(cursor.getString(1));

            }while (cursor.moveToNext());
        }

        return  criterio;
    }

    public List<Criterio> buscarCriterios()
    {
        List<Criterio> Listcriterio = new ArrayList<>();
        String[] colunas = new String[]{"_id", "nome"};


        Cursor cursor = bd.query("criterio", colunas, null, null, null, null, "_id ASC");

        if(cursor.getCount() > 0)
        {
            cursor.moveToNext();
            do
            {
                Criterio criterio = new Criterio();
                criterio.setId(cursor.getInt(0));
                criterio.setNome(cursor.getString(1));
                Listcriterio.add(criterio);

            }while (cursor.moveToNext());
        }

        return Listcriterio;
    }



    public AlternativaCriterio buscarAlternativaCriterioPorId(int id_alternativa, int id_criterio)
    {
        AlternativaCriterio alternativaCriterio = null;
        String[] colunas = new String[]{"*"};
        String where = "id_alternativa" + "=" + id_alternativa + " and " + " id_criterio" + "=" + id_criterio;
        Cursor cursor = bd.query("criterio_alternativa", colunas, where, null, null, null, "peso ASC");

        if(cursor.getCount() > 0)
        {

            cursor.moveToNext();
            do
            {
                alternativaCriterio = new AlternativaCriterio();
                alternativaCriterio.setId(cursor.getInt(0));
                alternativaCriterio.setAlternativa(this.buscaAlternativaPorId(cursor.getInt(1)));
                alternativaCriterio.setCriterio(this.bsucarCriterioPorId(cursor.getInt(2)));
                alternativaCriterio.setPeso(cursor.getDouble(3));

            }while (cursor.moveToNext());
        }

        return alternativaCriterio;
    }



    public List<AlternativaCriterio> buscarAlternativaCriterio()
    {
        List<AlternativaCriterio> listCriterio = new ArrayList<>();
        String[] colunas = new String[]{"_id", "id_alternativa", "id_criterio", "peso"};

        Cursor cursor = bd.query("criterio_alternativa", colunas, null, null, null, null, "_id ASC");

        if(cursor.getCount() > 0)
        {
            cursor.moveToNext();
            do
            {
                AlternativaCriterio alternativaCriterio = new AlternativaCriterio();
                alternativaCriterio.setId(cursor.getInt(0));
                alternativaCriterio.setAlternativa(this.buscaAlternativaPorId(cursor.getInt(1)));
                alternativaCriterio.setCriterio(this.bsucarCriterioPorId(cursor.getInt(2)));
                alternativaCriterio.setPeso(cursor.getDouble(3));
                listCriterio.add(alternativaCriterio);

            }while (cursor.moveToNext());
        }

        return listCriterio;
    }



}
