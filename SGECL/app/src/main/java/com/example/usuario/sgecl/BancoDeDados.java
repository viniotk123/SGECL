package com.example.usuario.sgecl;

/**
 * Created by Usuario on 04/10/2015.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDeDados extends SQLiteOpenHelper {
    private static final String NOME_BD = "aluno";
    private static final int VERSAO_BD	 = 1;

    public BancoDeDados(Context ctx) {
        super(ctx, NOME_BD, null, VERSAO_BD);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE aluno (_id INTEGER PRIMARY KEY, aluno TEXT, p1 REAL, p2 REAL, p3 REAL, p4 REAL, faltas INTEGER)");
        db.execSQL("CREATE TABLE mensagem (mensagem TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE aluno");
        db.execSQL("DROP TABLE mensagem");
        onCreate(db);

    }

}
