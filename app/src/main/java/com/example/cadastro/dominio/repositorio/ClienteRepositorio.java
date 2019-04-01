package com.example.cadastro.dominio.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.cadastro.MainActivity;
import com.example.cadastro.dominio.entidades.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepositorio {
    private SQLiteDatabase conexao;

    public ClienteRepositorio(SQLiteDatabase conexao) {
        this.conexao = conexao;
    }

    public void inserir(Cliente cliente){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME",cliente.Nome);
        contentValues.put("SENHA",cliente.Senha);
        contentValues.put("TELEFONE",cliente.Telefone);
        contentValues.put("EMAIL",cliente.Email);
        conexao.insertOrThrow("DATABASE",null,contentValues);
    }
    public void remover(int codigo){
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(codigo);

        conexao.delete("DATABASE","CODIGO = ?",parametros);
    }
    public void alterar(Cliente cliente){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME",cliente.Nome);
        contentValues.put("SENHA",cliente.Senha);
        contentValues.put("TELEFONE",cliente.Telefone);
        contentValues.put("EMAIL",cliente.Email);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(cliente.Codigo);

        conexao.update("DATABASE",contentValues,"CODIGO = ?",parametros);
    }
    public List<Cliente> buscarTodos(){
        List<Cliente> clientes = new ArrayList<Cliente>();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT CODIGO,NOME,SENHA,TELEFONE,EMAIL");
        sql.append("    FROM DATABASE ");

        Cursor resultado = conexao.rawQuery(sql.toString(),null);

        if(resultado.getCount() > 0){
            resultado.moveToFirst();

            do {
                Cliente cliente = new Cliente();
                cliente.Codigo = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));
                cliente.Nome = resultado.getString(resultado.getColumnIndexOrThrow("NOME"));
                cliente.Senha = resultado.getString(resultado.getColumnIndexOrThrow("SENHA"));
                cliente.Telefone = resultado.getString(resultado.getColumnIndexOrThrow("TELEFONE"));
                cliente.Email = resultado.getString(resultado.getColumnIndexOrThrow("EMAIL"));

                clientes.add(cliente);
            }while (resultado.moveToNext());
        }

        return clientes;
    }
    public Cliente buscarCliente(int codigo){
        StringBuilder sql = new StringBuilder();
        Cliente cliente = new Cliente();

        sql.append("SELECT CODIGO,NOME,SENHA,TELEFONE,EMAIL");
        sql.append("    FROM DATABASE ");
        sql.append(" WHERE CODIGO = ? ");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(codigo);

        Cursor resultado = conexao.rawQuery(sql.toString(),parametros);

        if(resultado.getCount() > 0){
            resultado.moveToFirst();
                cliente.Codigo = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));
                cliente.Nome = resultado.getString(resultado.getColumnIndexOrThrow("NOME"));
                cliente.Senha = resultado.getString(resultado.getColumnIndexOrThrow("SENHA"));
                cliente.Telefone = resultado.getString(resultado.getColumnIndexOrThrow("TELEFONE"));
                cliente.Email = resultado.getString(resultado.getColumnIndexOrThrow("EMAIL"));

                return cliente;
        }

        return null;
    }
}
