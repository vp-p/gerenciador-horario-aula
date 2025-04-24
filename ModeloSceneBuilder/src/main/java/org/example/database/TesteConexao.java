package org.example.database;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[] args) {
        Connection conexao = Conexao.conectar();
        if (conexao != null) {
            System.out.println("Conectado com sucesso!");
            try {
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
