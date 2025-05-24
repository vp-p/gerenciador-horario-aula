package org.example.dao;

import org.example.classes.Professor;
import org.example.database.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

   public void criarProfessor(String nome, String email)  {

        try (Connection con = Conexao.conectar()) {
            assert con != null;

            try (PreparedStatement stmt = con.prepareStatement("INSERT INTO professor(nome, email) VALUES(?,?)")) {
                stmt.setString(1, nome);
                stmt.setString(2, email);

                stmt.executeUpdate();

            }
        } catch (Exception e) {
            System.err.println("Erro ao gravar professor: " + e.getMessage());
        }

    }

    // Utilizado para atualizar a grade principal
    public List<Professor> buscarProfessores()  {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * from professor where deletado = FALSE";
        Connection conn = Conexao.conectar();
        PreparedStatement pst = null;

        try{
            pst = conn.prepareStatement(sql);

            ResultSet result = pst.executeQuery();

            while(result.next()){
                Professor professor = new Professor(result.getInt("id_professor"),result.getString("nome"), result.getString("email"));
                professores.add(professor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                if (conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return professores;
    }

    public Professor buscarPorId(int id) {
        String sql = "SELECT * FROM professor WHERE id_professor in (?)";
        Professor professor = null;

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setLong(1, id);

                ResultSet result = stmt.executeQuery();
                if (result.next()) {
                    professor = new Professor(result.getInt("id_professor"), result.getString("nome"), result.getString("email"));
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar professor: " + e.getMessage());
        }
        System.out.println(professor);
        return professor;
    }
//
    public void deletar(Integer id) {
//        String sql = "DELETE FROM professor WHERE id_professor = ?";
        String sql = "UPDATE professor SET deletado = TRUE WHERE id_professor = ?";

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, id);

                stmt.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println("Erro ao deletar professor: " + e.getMessage());
        }
    }

    public void alterarDados(Integer id,String nome, String email){
        String sql = "UPDATE professor " +
                "SET nome = ?, email = ? " +
                "WHERE (id_professor = ?)";

        Connection conn = Conexao.conectar();
        PreparedStatement pst = null;

        try{
            pst = conn.prepareStatement(sql);

            pst.setString(1, nome);
            pst.setString(2, email);
            pst.setInt(3, id);

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Professor> buscarPorNome(String nome){
       List<Professor> nomeProfessor = new ArrayList<>();
       String sql = "Select * From Professor where nome = ?";

       Connection conn = Conexao.conectar();
       PreparedStatement pst = null ;

       try{
           pst = conn.prepareStatement(sql);

           pst.setString(1, nome);

           ResultSet rs = pst.executeQuery();

           while(rs.next()){
               Professor professorlist = new Professor(rs.getInt("id_professor"),rs.getString("nome"), rs.getString("email"));
               nomeProfessor.add(professorlist);
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       return nomeProfessor;
    }
}