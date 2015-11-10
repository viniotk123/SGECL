package com.example.usuario.sgecl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String sLogin,sSenha;
    EditText login,senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        login=(EditText)findViewById(R.id.login);
        senha=(EditText)findViewById(R.id.senha);

        Button bt_aluno=(Button)findViewById(R.id.bt_aluno);
        Button bt_professor=(Button)findViewById(R.id.bt_professor);

        bt_aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sLogin=String.valueOf(login.getText());
                sSenha=String.valueOf(senha.getText());
                if(sLogin.equals("aluno") && sSenha.equals("aluno")){
                    Intent intent=new Intent(MainActivity.this,Alunotela.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Nome de usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        });


        bt_professor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sLogin=String.valueOf(login.getText());
                sSenha=String.valueOf(senha.getText());
                if(sLogin.equals("professor") && sSenha.equals("professor")){
                    Intent intent=new Intent(MainActivity.this,Professortela.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Nome de usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
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
