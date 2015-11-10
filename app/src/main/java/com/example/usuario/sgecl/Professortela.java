package com.example.usuario.sgecl;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by Usuario on 13/10/2015.
 */
public class Professortela extends AppCompatActivity {

    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professortela);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Button bt_arquivo=(Button) findViewById(R.id.bt_arquivo);
        final TextView txt_arquivo=(TextView) findViewById(R.id.txt_arquivo);
        bt_arquivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    BancoDeDados bancoDeDados = new BancoDeDados(getApplicationContext());
                    database = bancoDeDados.getWritableDatabase();
                    String bank= String.valueOf(database);

                    FileOutputStream arquivogravar=openFileOutput(txt_arquivo.getText().toString(), MODE_PRIVATE);

                    arquivogravar.write (bank.getBytes());//conteúdo aqui;
                    arquivogravar.close();
                    Toast.makeText(getApplicationContext(), "Arquivo gerado com sucesso", Toast.LENGTH_SHORT).show();

                }catch (Exception erro) {
                    Toast.makeText(getApplicationContext(), erro.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });



        Button bt_sair = (Button) findViewById(R.id.bt_sair);
        bt_sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Professortela.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button bt_cadastrar = (Button) findViewById(R.id.bt_cadastrar);
        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Professortela.this,Professor.class);
                startActivity(intent);
                finish();

            }
        });

        Button bt_mensagem=(Button)findViewById(R.id.bt_mensagem);
        bt_mensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Professortela.this,Mensagem.class);
                startActivity(intent);
                finish();

            }
        });

        Button bt_telaeditar=(Button)findViewById(R.id.bt_telaeditar);
        bt_telaeditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Professortela.this,Editar.class);
                startActivity(intent);
                finish();
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