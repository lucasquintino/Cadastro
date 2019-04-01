package com.example.cadastro.database;

public class ScriptDLL {

    public static String getCreateTableCliente(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE [DATABASE] (");
        sql.append("CODIGO   INTEGER       PRIMARY KEY AUTOINCREMENT\n" +
                "        NOT NULL,");
        sql.append("NOME     VARCHAR (250) NOT NULL,");
        sql.append("SENHA VARCHAR (255) NOT NULL,");
        sql.append("EMAIL    VARCHAR (200) NOT NULL,");
        sql.append("TELEFONE VARCHAR (20)  NOT NULL");
        sql.append(");");

        return sql.toString();

    }
}
