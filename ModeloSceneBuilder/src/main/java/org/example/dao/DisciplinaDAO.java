package org.example.dao;

import org.example.database.Conexao;

import java.sql.*;

public class DisciplinaDAO {

    public static void main(String[] args) {
        Connection con = Conexao.conectar();

        if (con != null) {
            try {
                // Primeiro, executa o INSERT
                String sqlInsert = "INSERT INTO professor (id_professor, nome, email) VALUES (?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(sqlInsert);

                pstmt.setInt(1, 1);
                pstmt.setString(2, "João da Silva");
                pstmt.setString(3, "joao@email.com");

                int linhasAfetadas = pstmt.executeUpdate();
                System.out.println("Inserção realizada. Linhas afetadas: " + linhasAfetadas);

                // Agora executa o SELECT para verificar o que foi inserido
                Statement stmt = con.createStatement();
                String sqlSelect = "SELECT * FROM professor";
                ResultSet rs = stmt.executeQuery(sqlSelect);

                while (rs.next()) {
                    int id = rs.getInt("id_professor");
                    String nome = rs.getString("nome");
                    String email = rs.getString("email");


                    System.out.println("ID: " + id + ", Nome: " + nome + ", Email: " + email);
                }

                // Fecha os recursos
                rs.close();
                stmt.close();
                pstmt.close();
                con.close();

            } catch (SQLException e) {
                System.err.println("Erro ao executar a operação: " + e.getMessage());
            }
        } else {
            System.out.println("Falha na conexão.");
        }
    }
}
