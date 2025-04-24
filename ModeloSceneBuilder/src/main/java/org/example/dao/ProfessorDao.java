package org.example.dao;

import com.mysql.cj.protocol.Resultset;
import org.example.database.Conexao;

import java.sql.*;

public class ProfessorDao {

            Connection conn = null;

            // Método para cadastrar um novo professor
    public void inserirNovoProfessor(String nome, String email) throws SQLException {
        conn = Conexao.conectar();
        PreparedStatement pst = null;
        ResultSet rs;
        try {
            pst = conn.prepareStatement(
                    "INSERT INTO professor(nome,email)"
                            + "VALUES (?,?)");
            pst.setString(1, nome);
            pst.setString(2, email);

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pst.close();
            conn.close();
        }
    }

    // Método para deletar o professor escolhido através do id
    public void deletarProfessor(Integer id) throws SQLException{
            Connection conn = Conexao.conectar();
            PreparedStatement pst = null;

            try{
                pst = conn.prepareStatement(
                        "DELETE FROM professor"
                            + "WHERE id = ?");
                pst.setInt(1, id);
                pst.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                pst.close();
                conn.close();
            }
    }

    // Método para atualizar os dados do professor escolhido através do id
    public void atualizarProfessor(Integer id, String nome, String email) throws SQLException{
        Connection conn = Conexao.conectar();
        PreparedStatement pst = null;

        try{
            pst = conn.prepareStatement(
                    "UPDATE professor"
                        + "SET nome = ?"
                        + "SET email = ?"
                        + "WHERE id = ? ");
            pst.setString(1, nome);
            pst.setString(2, email);
            pst.setInt(3, id);

            pst.executeUpdate();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        finally {
            pst.close();
            conn.close();
        }
    }
}

