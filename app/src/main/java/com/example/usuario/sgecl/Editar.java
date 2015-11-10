package com.example.usuario.sgecl;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by Usuario on 15/10/2015.
 */
public class Editar extends AppCompatActivity {

    private BancoDeDados bancoDeDados;
    private SQLiteDatabase database;
    String sAluno;
    Double sP1, sP2, sP3, sP4;
    Integer sFaltas;
    EditText aluno, p1, p2, p3, p4, faltas, nome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        nome=(EditText)findViewById(R.id.EPesquisar);

        aluno = (EditText) findViewById(R.id.Ealuno);

        p1 = (EditText) findViewById(R.id.Ep1);

        p2 = (EditText) findViewById(R.id.Ep2);

        p3 = (EditText) findViewById(R.id.Ep3);

        p4 = (EditText) findViewById(R.id.Ep4);

        faltas = (EditText)findViewById(R.id.Efaltas);



        Button button=(Button)findViewById(R.id.Ebt_pesquisar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    bancoDeDados = new BancoDeDados(getApplicationContext());
                    database = bancoDeDados.getReadableDatabase();

                    String Snome = String.valueOf(nome.getText());

                    Cursor cursor = database.rawQuery("SELECT * FROM aluno where aluno=('" + Snome + "')", null);

                    if (cursor.getCount() == 0) {
                        Toast.makeText(getApplicationContext(), "Não encontrado", Toast.LENGTH_SHORT).show();
                    } else {

                        cursor.moveToFirst();

                        aluno.setText(cursor.getString(cursor.getColumnIndex("aluno")));

                        p1.setText(String.valueOf(cursor.getDouble(cursor.getColumnIndex("p1"))));

                        p2.setText(String.valueOf(cursor.getDouble(cursor.getColumnIndex("p2"))));

                        p3.setText(String.valueOf(cursor.getDouble(cursor.getColumnIndex("p3"))));

                        p4.setText(String.valueOf(cursor.getDouble(cursor.getColumnIndex("p4"))));

                        faltas.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex("faltas"))));

                    }
                }catch (Exception erro){
                    Toast.makeText(getApplicationContext(), erro.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });







        Button bt_lancar = (Button) findViewById(R.id.Ebt_lancar);
        Button bt_voltar=(Button) findViewById(R.id.Ebt_voltar);

        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Editar.this,Professortela.class);
                startActivity(intent);
                finish();

            }
        });

        Button bt_limpar=(Button) findViewById(R.id.Ebt_limpar);
        bt_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aluno.setText(null);
                p1.setText(null);
                p2.setText(null);
                p3.setText(null);
                p4.setText(null);
                faltas.setText(null);
                nome.setText(null);
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
                    database.execSQL("UPDATE aluno SET p1=('"+sP1+"') WHERE aluno=('"+sAluno+"')");
                    database.execSQL("UPDATE aluno SET p2=('"+sP2+"') WHERE aluno=('"+sAluno+"')");
                    database.execSQL("UPDATE aluno SET p3=('"+sP3+"') WHERE aluno=('"+sAluno+"')");
                    database.execSQL("UPDATE aluno SET p4=('"+sP4+"') WHERE aluno=('"+sAluno+"')");
                    database.execSQL("UPDATE aluno SET faltas=('"+sFaltas+"') WHERE aluno=('"+sAluno+"')");



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
