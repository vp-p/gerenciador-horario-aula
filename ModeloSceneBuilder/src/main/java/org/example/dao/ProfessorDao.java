package org.example.dao;

import org.example.classes.Professor;
import org.example.database.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {
//    public Professor criar(Professor professor) {
//        String sql = "INSERT INTO professor (nome, email) VALUES (?, ?)";
//
//        try (Connection con = Conexao.conectar()) {
//            assert con != null;
//            try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//                stmt.setString(1, professor.getNome());
//                stmt.setString(2, professor.getEmail());
//
//                stmt.executeUpdate();
//
//                ResultSet result = stmt.getGeneratedKeys();
//                if (result.next()) {
//                    professor.setId(result.getLong(1));
//                }
//            }
//        } catch (Exception e) {
//            System.err.println("Erro ao gravar professor: " + e.getMessage());
//        }
//
//        return professor;
//    }

//    public void atualizar(Professor professor) {
//        String sql = "UPDATE professor SET nome = ?, email = ? WHERE id = ?";
//
//        try (Connection con = Conexao.conectar()) {
//            assert con != null;
//            try (PreparedStatement stmt = con.prepareStatement(sql)) {
//                stmt.setString(1, professor.getNome());
//                stmt.setString(2, professor.getEmail());
//                stmt.setLong(3, professor.getId());
//
//                stmt.executeUpdate();
//            }
//        } catch (Exception e) {
//            System.err.println("Erro ao atualizar professor: " + e.getMessage());
//        }
//    }

    public Professor buscarPorId(int id) {
        String sql = "SELECT * FROM professor WHERE id_professor = ?";
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
//    public void deletar(long id) {
//        String sql = "DELETE FROM professor WHERE id = ?";
//
//        try (Connection con = Conexao.conectar()) {
//            assert con != null;
//            try (PreparedStatement stmt = con.prepareStatement(sql)) {
//                stmt.setLong(1, id);
//
//                stmt.executeUpdate();
//            }
//        } catch (Exception e) {
//            System.err.println("Erro ao deletar professor: " + e.getMessage());
//        }
//    }
//
//    public List<Professor> listarTodos() {
//        String sql = "SELECT * FROM professor";
//        List<Professor> professores = new ArrayList<>();
//
//        try (Connection con = Conexao.conectar()) {
//            assert con != null;
//            try (PreparedStatement stmt = con.prepareStatement(sql)) {
//                ResultSet result = stmt.executeQuery();
//
//                while (result.next()) {
//                    Professor professor = new Professor(result.getString("nome"), result.getString("email"));
//                    professor.setId(result.getLong("id"));
//                    professores.add(professor);
//                }
//            }
//        } catch (Exception e) {
//            System.err.println("Erro ao listar professores: " + e.getMessage());
//        }
//
//        return professores;
//    }

}
