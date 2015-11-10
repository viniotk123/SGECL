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

/**
 * Created by Usuario on 15/10/2015.
 */
public class Mensagem extends AppCompatActivity{

    EditText et_mensagem;
    private BancoDeDados bancoDeDados;
    private SQLiteDatabase database;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensagem);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Button bt_voltaar=(Button)findViewById(R.id.bt_voltaar);
        bt_voltaar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Mensagem.this,Professortela.class);
                startActivity(intent);
                finish();
            }
        });

        et_mensagem=(EditText)findViewById(R.id.et_mensagem);
        Button bt_mensagem=(Button)findViewById(R.id.bt_mensagem);
        bt_mensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String mensagem=String.valueOf(et_mensagem.getText());


                    bancoDeDados = new BancoDeDados(getApplicationContext());
                    database=bancoDeDados.getWritableDatabase();

                    database.execSQL("INSERT INTO mensagem (mensagem) values ('"+mensagem+"')");



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

