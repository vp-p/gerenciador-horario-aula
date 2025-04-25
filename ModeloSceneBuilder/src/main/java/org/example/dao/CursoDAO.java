package org.example.dao;

import org.example.database.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public void criar(Curso curso) {
        String sql = "INSERT INTO cursos (nome, coordenador) VALUES (?, ?)";

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, curso.getNome());
                stmt.setString(2, curso.getCoordenador());

                stmt.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println("Erro ao gravar curso: " + e.getMessage());
        }
    }

    public void atualizar(Curso curso) {
        String sql = "UPDATE cursos SET nome = ?, coordenador = ? WHERE id = ?";

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, curso.getNome());
                stmt.setString(2, curso.getCoordenador());
                stmt.setLong(3, curso.getId());

                stmt.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println("Erro ao atualizar curso: " + e.getMessage());
        }
    }

    public Curso buscarPorId(long id) {
        String sql = "SELECT * FROM cursos WHERE id = ?";
        Curso curso = null;

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setLong(1, id);

                ResultSet result = stmt.executeQuery();
                if (result.next()) {
                    curso = new Curso(result.getString("nome"), result.getString("coordenador"));
                    curso.setId(result.getLong("id"));
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar curso por ID: " + e.getMessage());
        }
        return curso;
    }

    public void delete(long id) {
        String sql = "DELETE FROM cursos WHERE id = ?";

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setLong(1, id);

                stmt.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println("Erro ao deletar curso: " + e.getMessage());
        }
    }

    public List<Curso> listarTodos() throws SQLException {
        String sql = "SELECT * FROM cursos";
        List<Curso> cursos = new ArrayList<>();

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql);
                 ResultSet result = stmt.executeQuery()) {

                while (result.next()) {
                    Curso curso = new Curso(result.getString("nome"), result.getString("coordenador"));
                    curso.setId(result.getLong("id"));
                    cursos.add(curso);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar cursos: " + e.getMessage());
        }

        return cursos;
    }

}