package com.example.cadastro;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cadastro.database.DadosOpenHelper;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        criarConexao();
    }
    private void criarConexao(){
        try {
            dadosOpenHelper = new DadosOpenHelper(this);
            conexao = dadosOpenHelper.getWritableDatabase();
            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();

        }catch (SQLException ex){
            Toast.makeText(this, "erro", Toast.LENGTH_SHORT).show();
        }
    }
    public void cadastro(View view){
        Intent it = new Intent(MainActivity.this,Cadastro.class);
        startActivity(it);
    }
}