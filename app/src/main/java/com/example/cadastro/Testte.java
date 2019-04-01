package com.example.cadastro;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cadastro.database.DadosOpenHelper;
import com.example.cadastro.dominio.entidades.Cliente;
import com.example.cadastro.dominio.repositorio.ClienteRepositorio;

import java.util.List;

public class Testte extends AppCompatActivity {
    private RecyclerView listDados;
    private ClienteRepositorio clienteRepositorio;
    private NewRecyclerView newRecyclerView;
    private DadosOpenHelper dadosOpenHelper;
    private SQLiteDatabase conexao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
        criarConexao();
        listDados = findViewById(R.id.listDados);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listDados.setLayoutManager(linearLayoutManager);
        listDados.setHasFixedSize(true);
        clienteRepositorio = new ClienteRepositorio(conexao);
        List<Cliente> dados = clienteRepositorio.buscarTodos();
        newRecyclerView = new NewRecyclerView(dados);
        listDados.setAdapter(newRecyclerView);
        listDados.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), listDados, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(Testte.this, "OK", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

    }

    private void criarConexao() {
        try {
            dadosOpenHelper = new DadosOpenHelper(this);
            conexao = dadosOpenHelper.getWritableDatabase();
            clienteRepositorio = new ClienteRepositorio(conexao);
            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();

        } catch (SQLException ex) {
            Toast.makeText(this, "erro", Toast.LENGTH_SHORT).show();
        }
    }
    public void voltar(View view){
        Intent intent = new Intent(Testte.this, Cadastro.class);
        startActivity(intent);
    }
}
