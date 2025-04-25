package org.example.dao;

import org.example.classes.Aula;
import org.example.classes.Curso;
import org.example.classes.Disciplina;
import org.example.classes.Professor;
import org.example.database.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AulaDAO {

    public void criar(Aula aula) {
        String sql = "INSERT INTO aula (id, id_professor, id_disciplina, id_curso, inicio_aula, fim_aula) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setLong(1, aula.getId());
                stmt.setLong(2, aula.getIdProfessor().getId());
                stmt.setLong(3, aula.getIdDisciplina().getId());
                stmt.setLong(4, aula.getIdCurso().getId());
                stmt.setTime(5, java.sql.Time.valueOf(aula.getInicioAula()));
                stmt.setTime(6, java.sql.Time.valueOf(aula.getFimAula()));

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao gravar aula: " + e.getMessage());
        }
    }

    public void atualizar(Aula aula) {
        String sql = "UPDATE aula SET id_professor = ?, id_disciplina = ?, id_curso = ?, inicio_aula = ?, fim_aula = ? WHERE id = ?";

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, aula.getIdProfessor().getNome());
                stmt.setString(2, aula.getIdDisciplina().getNome());
                stmt.setString(3, aula.getIdCurso().getNome());
                stmt.setTime(4, java.sql.Time.valueOf(aula.getInicioAula()));
                stmt.setTime(5, java.sql.Time.valueOf(aula.getFimAula()));
                stmt.setLong(6, aula.getId());

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
        }
    }

    public Aula buscarPorId(long id) {
        String sql = "SELECT * FROM aula WHERE id = ?";
        Aula aula = null;

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setLong(1, id);

                // Execute the query and process the result set
                // ...
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar aula por ID: " + e.getMessage());
        }
        return aula;
    }


    public void delete(long id) {
        String sql = "DELETE FROM aula WHERE id = ?";

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setLong(1, id);

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar aula: " + e.getMessage());
        }
    }

    public List<Aula> listarTodos() {
        String sql = "SELECT * FROM aula";
        List<Aula> aulas = new ArrayList<>();

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql);
                 ResultSet result = stmt.executeQuery()) {

                while (result.next()) {
                    ProfessorDAO professorDAO = new ProfessorDAO();
                    DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
                    CursoDAO cursoDAO = new CursoDAO();

                    Professor professor = professorDAO.buscarPorId(result.getLong("id_professor"));
                    Disciplina disciplina = disciplinaDAO.buscarPorId(result.getLong("id_disciplina"));
                    Curso curso = cursoDAO.buscarPorId(result.getLong("id_curso"));
                }
            } catch (SQLException e) {
                System.err.println("Erro ao listar aulas: " + e.getMessage());
            }

            return aulas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
