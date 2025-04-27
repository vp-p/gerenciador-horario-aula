package org.example.dao;

import org.example.classes.Disciplina;
import org.example.database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

//    public void criar(Disciplina disciplina) {
//        String sql = "INSERT INTO disciplina (nome, carga_horaria, id_professor, id_curso) VALUES (?, ?, ?, ?)";
//
//        try (Connection con = Conexao.conectar()) {
//            assert con != null;
//            try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//
//                stmt.setString(1, disciplina.getNome());
//                stmt.setLong(2, disciplina.getCargaHoraria());
//                stmt.setLong(3, disciplina.getProfessor().getId());
//
//                stmt.executeUpdate();
//
//                ResultSet result = stmt.getGeneratedKeys();
//                if (result.next()) {
//                    disciplina.setId(result.getLong(1));
//                }
//            }
//        } catch (SQLException e) {
//            System.err.println("Erro ao gravar disciplina: " + e.getMessage());
//        }
//    }

//    public void atualizar(Disciplina disciplina) {
//        String sql = "UPDATE disciplina SET nome = ?, carga_horaria = ?, id_professor = ?, id_curso = ? WHERE id = ?";
//
//        try (Connection con = Conexao.conectar()) {
//            assert con != null;
//            try (PreparedStatement stmt = con.prepareStatement(sql)) {
//
//                stmt.setString(1, disciplina.getNome());
//                stmt.setLong(2, disciplina.getCargaHoraria());
//                stmt.setLong(3, disciplina.getProfessor().getId());
//                stmt.setLong(4, disciplina.getCurso().getId());
//                stmt.setLong(5, disciplina.getId());
//
//                stmt.executeUpdate();
//            }
//        } catch (SQLException e) {
//            System.err.println("Erro ao atualizar disciplina: " + e.getMessage());
//        }
//    }

//    public Disciplina buscarPorId(long id) {
//        String sql = "SELECT * FROM disciplina WHERE id = ?";
//        Disciplina disciplina = null;
//
//        try (Connection con = Conexao.conectar()) {
//            assert con != null;
//            try (PreparedStatement stmt = con.prepareStatement(sql)) {
//
//                stmt.setLong(1, id);
//                ResultSet rs = stmt.executeQuery();
//
//                if (rs.next()) {
//                    disciplina = new Disciplina(
//                            rs.getString("nome"),
//                            rs.getInt("carga_horaria"),
//                            null,
//                            null
//                    );
//                    disciplina.setId(rs.getLong("id"));
//                }
//            }
//        } catch (SQLException e) {
//            System.err.println("Erro ao buscar disciplina: " + e.getMessage());
//        }
//        return disciplina;
//    }

//    public void delete(Disciplina disciplina) {
//        String sql = "DELETE FROM disciplina WHERE id = ?";
//
//        try (Connection con = Conexao.conectar()) {
//            assert con != null;
//            try (PreparedStatement stmt = con.prepareStatement(sql)) {
//
//                stmt.setLong(1, disciplina.getId());
//
//                stmt.executeUpdate();
//
//            }
//        } catch (SQLException e) {
//            System.err.println("Erro ao deletar disciplina: " + e.getMessage());
//        }
//}
        public static List<Disciplina> listarPorCurso(int id_curso) {
            List <Disciplina> disciplinas = new ArrayList<>();
            String sql = "SELECT * FROM disciplina where id_curso = "+id_curso;

            try (Connection con = Conexao.conectar()) {
                assert con != null;
                try (PreparedStatement stmt = con.prepareStatement(sql);
                     ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {

                        Disciplina d = new Disciplina(
                                rs.getInt("id_disciplina"),
                                rs.getString("nome"),
                                rs.getInt("id_professor"),
                                rs.getInt("id_curso"),
                                rs.getInt("semestre")
                        );
                        disciplinas.add(d);
                    }
                    System.out.println(disciplinas);
                }
            } catch (SQLException e) {
                System.err.println("Erro ao listar disciplinas: " + e.getMessage());
            }

            return disciplinas;
    }
}