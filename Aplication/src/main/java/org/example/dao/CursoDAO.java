package org.example.dao;

import org.example.classes.Curso;
import org.example.classes.Disciplina;
import org.example.database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public void criar(Curso curso) {
        String sql = "INSERT INTO curso (nome, coordenador, periodo) VALUES (?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCoordenador());
            stmt.setString(3, curso.getPeriodo());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao gravar curso: " + e.getMessage());
        }
    }

    public List<Curso> buscarPorId(int id) {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM curso WHERE id_curso AND deletado = 0 = ?, ?";

        Connection conn = Conexao.conectar();
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(sql);

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Curso curso = new Curso(rs.getString("nome"), rs.getString("coordenador"), rs.getString("periodo"));
                cursos.add(curso);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cursos;
    }

    public List<Curso> listarTodos() throws SQLException {
        String sql = "SELECT id_curso, nome, coordenador, periodo, deletado FROM curso WHERE deletado = 0";
        List<Curso> lista = new ArrayList<>();

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Curso c = new Curso(
                        false,
                        rs.getLong("id_curso"),
                        rs.getString("nome"),
                        rs.getString("coordenador"),
                        rs.getString("periodo"),
                        rs.getBoolean("deletado")
                );
                lista.add(c);
            }
        }

        return lista;
    }

    public void delete(long id) {
        String sql = "UPDATE curso SET deletado = 1 WHERE id_curso = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
            System.out.println("Curso marcado como deletado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar curso: " + e.getMessage());
        }
    }
}
