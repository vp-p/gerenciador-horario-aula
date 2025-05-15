package org.example.dao;

import org.example.classes.Curso;
import org.example.classes.Disciplina;
import org.example.classes.Professor;
import org.example.database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    public void deleteDisciplina(long id) {
        String sql = "UPDATE disciplilna SET deletado = 1 WHERE id_disciplina = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
            System.out.println("Disciplina marcada como deletado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar disciplina: " + e.getMessage());
        }
    }

    public void criarDisciplina(Disciplina disciplina) {
        String sql = "INSERT INTO disciplina (nome, carga_horaria, id_professor, id_curso) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, disciplina.getNome());
                stmt.setInt(2, disciplina.getCargaHoraria());
                stmt.setInt(3, disciplina.getProfessor());

                stmt.executeUpdate();

                ResultSet result = stmt.getGeneratedKeys();
                if (result.next()) {
                    disciplina.setId(result.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao gravar disciplina: " + e.getMessage());
        }
    }

    public void atualizarDisciplina(Disciplina disciplina) {
        String sql = "UPDATE disciplina SET nome = ?, carga_horaria = ?, id_professor = ?, id_curso = ? WHERE id = ?";

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setString(1, disciplina.getNome());
                stmt.setLong(2, disciplina.getCargaHoraria());
                stmt.setLong(3, disciplina.getProfessor());
                stmt.setLong(4, disciplina.getCurso());
                stmt.setLong(5, disciplina.getId());

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar disciplina: " + e.getMessage());
        }
    }

    public Disciplina buscarPorId(long id) {
        String sql = "SELECT * FROM disciplina WHERE id = ?";

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setLong(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String nome = rs.getString("nome");
                        int semestre = rs.getInt("semestre");
                        int idProfessor = rs.getInt("id_professor");
                        int idCurso = rs.getInt("id_curso");
                        int cargaHoraria = rs.getInt("carga_horaria");

                        CursoDAO cursoDAO = new CursoDAO();
                        ProfessorDAO professorDAO = new ProfessorDAO();

                        Curso curso = cursoDAO.buscarPorId(idCurso);
                        Professor professor = professorDAO.buscarPorId(idProfessor);

                        Disciplina disciplina = new Disciplina(nome, professor, curso, semestre, cargaHoraria);
                        disciplina.setId(rs.getInt("id"));
                        return disciplina;
                    }
                }
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


//        Disciplina disciplina = new Disciplina(nome, professor, curso, semestre, cargaHoraria);
//        disciplina.setId(rs.getInt("id"));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static List<Disciplina> listarPorCurso(int idCurso) {
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT * FROM disciplina WHERE id_curso = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idCurso);
            ResultSet rs = stmt.executeQuery();

            CursoDAO cursoDAO = new CursoDAO();
            ProfessorDAO professorDAO = new ProfessorDAO();

            while (rs.next()) {
                int idProfessor = rs.getInt("id_professor");
                int idCursoBanco = rs.getInt("id_curso");

                Professor professor = professorDAO.buscarPorId(idProfessor);
                Curso curso = cursoDAO.buscarPorId(idCursoBanco);

                Disciplina disciplina = new Disciplina(
                        rs.getString("nome"),
                        professor,
                        curso,
                        rs.getInt("semestre"),
                        rs.getInt("carga_horaria")
                );

                disciplina.setId(rs.getInt("id")); // se tiver um ID
                disciplinas.add(disciplina);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar disciplinas: " + e.getMessage());
        }

        return disciplinas;
    }
}

