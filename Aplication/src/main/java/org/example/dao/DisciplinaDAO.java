package org.example.dao;

import org.example.classes.Curso;
import org.example.classes.Disciplina;
import org.example.classes.Professor;
import org.example.database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    /*public List<Disciplina> listarTodos() {
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT * FROM disciplina WHERE deletado = 0";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Disciplina disciplina = new Disciplina(
                        rs.getInt("id_disciplina"),
                        rs.getString("nome"),
                        rs.getInt("id_professor"),
                        rs.getInt("id_curso"),
                        rs.getInt("semestre")
                );
//                disciplina.setId(rs.getInt("id_disciplina"));
                disciplinas.add(disciplina);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar disciplinas: " + e.getMessage());
        }

        return disciplinas;
    }
*/

    public List<Disciplina> listarTodos() {
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT d.id_disciplina, d.nome, d.id_professor, d.id_curso, d.semestre, " +
                "p.nome AS nome_professor, c.nome AS nome_curso " +
                "FROM disciplina d " +
                "LEFT JOIN professor p ON d.id_professor = p.id_professor " +
                "LEFT JOIN curso c ON d.id_curso = c.id_curso " +
                "WHERE d.deletado = 0";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Disciplina disciplina = new Disciplina(
                        rs.getInt("id_disciplina"),
                        rs.getString("nome"),
                        rs.getInt("id_professor"),
                        rs.getInt("id_curso"),
                        rs.getInt("semestre"),
                        rs.getString("nome_professor"),
                        rs.getString("nome_curso")
                );
                disciplinas.add(disciplina);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar disciplinas: " + e.getMessage());
        }

        return disciplinas;
    }

    public void deleteDisciplina(Integer id) {
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
        String sql = "INSERT INTO disciplina (nome, id_professor, id_curso, semestre) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, disciplina.getNome());
                stmt.setInt(2, disciplina.getIdProfessor());
                stmt.setInt(3, disciplina.getIdCurso());
                stmt.setInt(4, disciplina.getSemestre());

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
        String sql = "UPDATE disciplina SET nome = ?, id_professor = ?, id_curso = ? WHERE id = ?";

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setString(1, disciplina.getNome());
                stmt.setLong(3, disciplina.getIdProfessor());
                stmt.setLong(4, disciplina.getIdCurso());
                stmt.setLong(5, disciplina.getId());

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar disciplina: " + e.getMessage());
        }
    }

    public Disciplina buscarDisciplina(long id) {
        String sql = "SELECT * FROM disciplina WHERE id = ? AND deletado = 0";

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setLong(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String nome = rs.getString("nome");
                        int idProfessor = rs.getInt("id_professor");
                        int idCurso = rs.getInt("id_curso");
                        int semestre = rs.getInt("semestre");


/*
                        CursoDAO cursoDAO = new CursoDAO();
                        ProfessorDAO professorDAO = new ProfessorDAO();
*/

/*                        Curso curso = cursoDAO.buscarPorId(idCurso);
                        Professor professor = professorDAO.buscarPorId(idProfessor);*/

                        Disciplina disciplina = new Disciplina(nome, idProfessor, idCurso, semestre);
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

    public static List<Curso> listarCursos() {
        List<Curso> nomeCurso = new ArrayList<>();
        String sql = "SELECT * FROM curso WHERE deletado = 0";

        Connection conn = Conexao.conectar();
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Curso cursoList = new Curso(
                        rs.getInt("id_curso"),
                        rs.getString("nome"),
                        rs.getString("coordenador"),
                        rs.getString("periodo")
                );

                nomeCurso.add(cursoList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nomeCurso;
    }


    public static List<Professor> listarProfessores() {
        List<Professor> nomeProfessor = new ArrayList<>();
        String sql = "SELECT * FROM professor WHERE deletado = 0";

        Connection conn = Conexao.conectar();
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Professor profList = new Professor(
                        rs.getInt("id_professor"),
                        rs.getString("nome"),
                        rs.getString("email")
                );

                nomeProfessor.add(profList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nomeProfessor;
    }


    public List<Disciplina> buscarPorNome(String nome) {
        List<Disciplina> nomeDisciplina = new ArrayList<>();
        String sql = "SELECT * FROM Disciplina WHERE nome AND deletado = 0 = ?";

        Connection conn = Conexao.conectar();
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(sql);

            pst.setString(1, nome);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Disciplina disciList = new Disciplina(rs.getString("nome"), rs.getInt("id_professor"), rs.getInt("id_curso"), rs.getInt("semestre"));
                nomeDisciplina.add(disciList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nomeDisciplina;
    }

    public static List<Disciplina> listarPorCurso(int idCurso) {
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT * FROM disciplina WHERE id_curso = ? AND deletado = 0";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idCurso);
            ResultSet rs = stmt.executeQuery();

            CursoDAO cursoDAO = new CursoDAO();
            ProfessorDAO professorDAO = new ProfessorDAO();

            while (rs.next()) {
                Disciplina disciplina = new Disciplina(
                        rs.getString("nome"),
                        rs.getInt("id_professor"),
                        rs.getInt("id_curso"),
                        rs.getInt("semestre")
                );
                disciplina.setId(rs.getInt("id_disciplina"));
                disciplinas.add(disciplina);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar disciplinas: " + e.getMessage());
        }

        return disciplinas;
    }

    public static List<Disciplina> listarPorProfessor(int idProfessor) {
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT * FROM disciplina WHERE id_professor = ? AND deletado = false";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idProfessor);
            ResultSet rs = stmt.executeQuery();

            CursoDAO cursoDAO = new CursoDAO();
            ProfessorDAO professorDAO = new ProfessorDAO();

            while (rs.next()) {
                Disciplina disciplina = new Disciplina(
                        rs.getString("nome"),
                        rs.getInt("id_professor"),
                        rs.getInt("id_curso"),
                        rs.getInt("semestre")
                );
                disciplina.setId(rs.getInt("id"));
                disciplinas.add(disciplina);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar disciplinas: " + e.getMessage());
        }

        return disciplinas;
    }

    public void deletarDisciplina(Disciplina disciplina) {
        String sql = "UPDATE disciplina SET deletado = TRUE WHERE id_disciplina = ? AND nome = ? AND id_professor = ? AND id_curso = ? AND semestre = ?;";
        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, disciplina.getNome());
                stmt.setInt(2, disciplina.getIdProfessor());
                stmt.setInt(3, disciplina.getIdCurso());
                stmt.setInt(4, disciplina.getSemestre());

                stmt.executeUpdate();

                ResultSet result = stmt.getGeneratedKeys();
                if (result.next()) {
                    disciplina.setId(result.getInt(1));
                }
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}