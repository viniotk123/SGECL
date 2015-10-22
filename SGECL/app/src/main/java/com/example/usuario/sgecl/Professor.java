package com.example.usuario.sgecl;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Usuario on 04/10/2015.
 */
public class Professor extends AppCompatActivity {
    private BancoDeDados bancoDeDados;
    private SQLiteDatabase database;
    String sAluno;
    Double sP1, sP2, sP3, sP4;
    Integer sFaltas;
    EditText aluno, p1, p2, p3, p4, faltas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        aluno = (EditText) findViewById(R.id.aluno);

        p1 = (EditText) findViewById(R.id.p1);

        p2 = (EditText) findViewById(R.id.p2);

        p3 = (EditText) findViewById(R.id.p3);

        p4 = (EditText) findViewById(R.id.p4);

        faltas = (EditText) findViewById(R.id.faltas);

        Button bt_lancar = (Button) findViewById(R.id.bt_lancar);
        Button bt_voltar=(Button) findViewById(R.id.bt_voltar);

        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Professor.this,Professortela.class);
                startActivity(intent);
                finish();

            }
        });

        Button bt_limpar=(Button) findViewById(R.id.bt_limpar);
        bt_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aluno.setText(null);
                p1.setText(null);
                p2.setText(null);
                p3.setText(null);
                p4.setText(null);
                faltas.setText(null);
            }
        });

        bt_lancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String aux;
                    sAluno = String.valueOf(aluno.getText());
                    aux = String.valueOf(p1.getText());
                    sP1 = Double.parseDouble(aux);
                    aux = String.valueOf(p2.getText());
                    sP2 = Double.parseDouble(aux);
                    aux = String.valueOf(p3.getText());
                    sP3 = Double.parseDouble(aux);
                    aux = String.valueOf(p4.getText());
                    sP4 = Double.parseDouble(aux);
                    aux = String.valueOf(faltas.getText());
                    sFaltas = Integer.parseInt(aux);


                    bancoDeDados = new BancoDeDados(getApplicationContext());
                    database = bancoDeDados.getWritableDatabase();
                    database.execSQL("INSERT INTO aluno (aluno,p1,p2,p3,p4,faltas) values ('" + sAluno + "','" + sP1 + "','" + sP2 + "','" + sP3 + "','" + sP4 + "','" + sFaltas + "')");

                    Toast.makeText(getApplicationContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();


                } catch (Exception erro) {
                    Toast.makeText(getApplicationContext(), erro.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
