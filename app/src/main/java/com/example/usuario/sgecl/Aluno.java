package com.example.usuario.sgecl;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Usuario on 04/10/2015.
 */
public class Aluno extends AppCompatActivity{

    private BancoDeDados bancoDeDados;
    private SQLiteDatabase database;
    EditText pesquisar;
    String nome;
    GridView tabela;
    TextView tv_nome,tv_n1,tv_n2,tv_n3,tv_n4,tv_faltas,tv_mensagem, somatorio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aluno);
        pesquisar=(EditText)findViewById(R.id.Pesquisar);
        tv_nome=(TextView) findViewById(R.id.tv_nome);
        tv_n1=(TextView) findViewById(R.id.tv_n1);
        tv_n2=(TextView) findViewById(R.id.tv_n2);
        tv_n3=(TextView) findViewById(R.id.tv_n3);
        tv_n4=(TextView) findViewById(R.id.tv_n4);
        tv_faltas=(TextView) findViewById(R.id.tv_faltas);
        tv_mensagem=(TextView)findViewById(R.id.tv_mensagem);
        somatorio=(TextView) findViewById(R.id.somatorio);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);








        Button bt_pesquisar = (Button)findViewById(R.id.bt_pesquisar);

        bt_pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                try{
                    bancoDeDados=new BancoDeDados(getApplicationContext());
                    database= bancoDeDados.getWritableDatabase();
                    nome=String.valueOf(pesquisar.getText());
                    Cursor cursor=database.rawQuery("SELECT * FROM aluno WHERE aluno=('"+nome+"')",null);

                    Cursor cursorMensagem=database.rawQuery("SELECT * FROM mensagem",null);
                    cursorMensagem.moveToLast();

if (cursor.getCount()==0){
    Toast.makeText(getApplicationContext(),"Não encontrado", Toast.LENGTH_SHORT).show();
}else{

    tv_mensagem.setText(null);
    cursor.moveToFirst();

    tv_nome.setText(cursor.getString(cursor.getColumnIndex("aluno")));

    tv_n1.setText(String.valueOf(cursor.getDouble(cursor.getColumnIndex("p1"))));

    tv_n2.setText(String.valueOf(cursor.getDouble(cursor.getColumnIndex("p2"))));

    tv_n3.setText(String.valueOf(cursor.getDouble(cursor.getColumnIndex("p3"))));

    tv_n4.setText(String.valueOf(cursor.getDouble(cursor.getColumnIndex("p4"))));

    tv_faltas.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex("faltas"))));

    tv_mensagem.setText(cursorMensagem.getString(cursorMensagem.getColumnIndex("mensagem")));



}

                }catch (Exception erro){
                    Toast.makeText(getApplicationContext(),erro.getMessage(), Toast.LENGTH_SHORT).show();
                }
                Double nota1= Double.parseDouble(String.valueOf(tv_n1.getText()));
                Double nota2= Double.parseDouble(String.valueOf(tv_n2.getText()));
                Double nota3= Double.parseDouble(String.valueOf(tv_n3.getText()));
                Double nota4= Double.parseDouble(String.valueOf(tv_n4.getText()));
                Double total= nota1+nota2+nota3+nota4;
                Double totalf=Double.parseDouble((String.valueOf(tv_faltas.getText())));

                if (total<12){
                    somatorio.setText(String.valueOf(total));
                    somatorio.setTextColor(Color.parseColor("#FF0000"));
                } else{
                    somatorio.setText(String.valueOf(total));
                    somatorio.setTextColor(Color.parseColor("#000000"));
                }

                if (totalf>38){
                    tv_faltas.setTextColor(Color.parseColor("#FF0000"));
                } else{
                    tv_faltas.setTextColor(Color.parseColor("#000000"));
                }



            }
        });
        Button bt_inicio=(Button) findViewById(R.id.bt_inicio);
        bt_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Aluno.this,Alunotela.class);
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
