package com.example.cadastro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cadastro.database.DadosOpenHelper;
import com.example.cadastro.dominio.entidades.Cliente;
import com.example.cadastro.dominio.repositorio.ClienteRepositorio;

public class Cadastro extends AppCompatActivity {
    private EditText editNome;
    private EditText editEmail;
    private EditText editSenha;
    private EditText editTelefone;

    private ClienteRepositorio clienteRepositorio;
    private DadosOpenHelper dadosOpenHelper;
    private SQLiteDatabase conexao;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        editNome = findViewById(R.id.nome);
        editEmail = findViewById(R.id.email);
        editSenha = findViewById(R.id.senha);
        editTelefone = findViewById(R.id.telefone);
        criarConexao();
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

    public void cadastrar(View view) {
        cliente = new Cliente();
        if(validarDados()){
            try {
                clienteRepositorio.inserir(cliente);

                Intent intent = new Intent(Cadastro.this, Testte.class);
                startActivity(intent);
                finish();
            }catch (SQLException ex) {
                Toast.makeText(this, "erro", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public boolean validarDados() {
        String nome = editNome.getText().toString();
        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();
        String telefone = editTelefone.getText().toString();

        cliente.Nome = nome;
        cliente.Email = email;
        cliente.Senha = senha;
        cliente.Telefone = telefone;


        boolean v = false;

        if (!(isEmailValido(email))) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Ocorreu um erroo!").setMessage("O seu email não foi preenchido corretamente").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    editEmail.requestFocus();
                }
            }).show();
        } else if (senha == null || senha.equals("")) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Ocorreu um erroo!").setMessage("A seu senha não foi preenchido, por favor preencha antes de enviar!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    editSenha.requestFocus();
                }
            }).show();
        } else if (nome == null || nome.equals("")) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Ocorreu um erroo!").setMessage("O seu nome não foi preenchido, por favor preencha antes de enviar!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    editNome.requestFocus();
                }
            }).show();
        } else if (telefone == null || telefone.equals("")) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Ocorreu um erroo!").setMessage("O seu numero não foi preenchido, por favor preencha antes de enviar!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    editTelefone.requestFocus();
                }
            }).show();
        } else{
            v = true;
        }
        return v;
    }

    public boolean isEmailValido(String email) {
        if (!(email == null || email.equals("")) && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        return false;
    }

    public void passarDados(String nome, String email, String telefone, String senha){
        Intent intent = new Intent(Cadastro.this, Testte.class);
        intent.putExtra("name", nome);
        intent.putExtra("password", senha);
        intent.putExtra("number", telefone);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}
