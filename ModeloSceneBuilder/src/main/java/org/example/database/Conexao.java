package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/bancoapi2";
    private static final String USUARIO = "root";
    private static final String SENHA = "fatec";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }
}
