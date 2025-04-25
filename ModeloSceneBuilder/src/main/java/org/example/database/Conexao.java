package org.example.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    static Dotenv dotenv = Dotenv.load();

   private static String host = dotenv.get("DB_HOST");
   private static String port = dotenv.get("DB_PORT");
   private static String name = dotenv.get("DB_NAME");
   private static String user = dotenv.get("DB_USER");
   private static String password = dotenv.get("DB_PASSWORD");

    static String url = "jdbc:mysql://" + host + ":" + port + "/" + name;

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }
}
