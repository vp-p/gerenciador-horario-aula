package org.example.dao;

import javafx.scene.control.Alert;
import org.example.classes.Aula;
import org.example.classes.Curso;
import org.example.classes.Disciplina;
import org.example.database.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AulaDAO {

    public void criar(Aula aula, int semestre) throws SQLException {

        List<Aula> conflito =  BuscarConflito(aula);
        List<Aula> conflito2 =  BuscarConflito2(aula, semestre);
        if(conflito.isEmpty() && conflito2.isEmpty()){
            String sql = "INSERT INTO aula (id_professor, id_disciplina, id_curso, dia_semana, numero_aula, periodo) VALUES (?, ?, ?, ?, ?, ?)";

            try (Connection con = Conexao.conectar()) {
                assert con != null;
                try (PreparedStatement stmt = con.prepareStatement(sql)) {

                    stmt.setLong(1, aula.getIdProfessor());
                    stmt.setLong(2, aula.getIdDisciplina());
                    stmt.setLong(3, aula.getIdCurso());
                    stmt.setString(4, aula.getDiaSemana());
                    stmt.setInt(5, aula.getNumeroAula());
                    stmt.setString(6, aula.getPeriodo());

                    stmt.executeUpdate();
                }
                // Exibir mensagem de sucesso
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText("Aula cadastrada com sucesso!");
                alert.setContentText("A aula foi adicionada à grade horária.");
                alert.showAndWait();
            } catch (SQLException e) {
                System.err.println("Erro ao gravar aula: " + e.getMessage());
            }
        }else{
            // Exibir mensagem de sucesso
            List<Integer> professores = buscarProfessoresPorCursoESemestre(aula, semestre);
            List<Integer> Ocupados = buscarProfessoresOcupados( aula, professores);

            professores.removeAll(Ocupados);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erro");
            alert.setHeaderText("Aula Não cadastrada!");
            alert.setContentText("A aula pode estar conflitando com outros horarios \n. " +
                    "Segue os professores disponiveis para esse horario:");
            alert.showAndWait();
        }

    }

//    public void atualizar(Aula aula) {
//        String sql = "UPDATE aula SET id_professor = ?, id_disciplina = ?, id_curso = ?, inicio_aula = ?, fim_aula = ? WHERE id = ?";
//
//        try (Connection con = Conexao.conectar()) {
//            assert con != null;
//            try (PreparedStatement stmt = con.prepareStatement(sql)) {
//                stmt.setString(1, aula.getIdProfessor().getNome());
//                stmt.setString(2, aula.getIdDisciplina().getNome());
//                stmt.setString(3, aula.getIdCurso().getNome());
//                stmt.setTime(4, java.sql.Time.valueOf(aula.getInicioAula()));
//                stmt.setTime(5, java.sql.Time.valueOf(aula.getFimAula()));
//                stmt.setLong(6, aula.getId());
//
//                stmt.executeUpdate();
//            }
//        } catch (SQLException e) {
//        }
//    }

//    public Aula buscarPorId(long id) {
//        String sql = "SELECT * FROM aula WHERE id = ?";
//        Aula aula = null;
//
//        try (Connection con = Conexao.conectar()) {
//            assert con != null;
//            try (PreparedStatement stmt = con.prepareStatement(sql)) {
//                stmt.setLong(1, id);
//
//                // Execute the query and process the result set
//                // ...
//            }
//        } catch (SQLException e) {
//            System.err.println("Erro ao buscar aula por ID: " + e.getMessage());
//        }
//        return aula;
//    }


//    public void delete(long id) {
//        String sql = "DELETE FROM aula WHERE id = ?";
//
//        try (Connection con = Conexao.conectar()) {
//            assert con != null;
//            try (PreparedStatement stmt = con.prepareStatement(sql)) {
//                stmt.setLong(1, id);
//
//                stmt.executeUpdate();
//            }
//        } catch (SQLException e) {
//            System.err.println("Erro ao deletar aula: " + e.getMessage());
//        }
//    }

    public List<Aula> BuscarConflito(Aula aula) throws SQLException {

        String sql ="SELECT * FROM aula where "+
         "id_professor ="+ aula.getIdProfessor() +
                " and numero_aula = " + aula.getNumeroAula()+
                " and dia_semana = '"+aula.getDiaSemana()+"'";

        System.out.println(sql);
        List<Aula> aulas = new ArrayList<>();

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql);
                 ResultSet result = stmt.executeQuery()) {

                while (result.next()) {
                    Aula aulaa = new Aula(
                            result.getInt("idaula"),
                            result.getInt("id_professor"),
                            result.getInt("id_disciplina"),
                            result.getInt("id_curso"),
                            result.getString("dia_semana"),
                            result.getInt("numero_aula"),
                              "",
                            result.getString("periodo")
                    );

                    aulas.add(aulaa);
                }
                System.out.println("aulas: "+ aulas);
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar aulas: " + e.getMessage());
        }

        return aulas;
    }

    public List<Aula> BuscarConflito2(Aula aula, int semestre) throws SQLException {

        String sql ="SELECT * FROM aula au "+
        "inner join disciplina di on au.id_disciplina = di.id_disciplina "+
        "where au.id_curso = "+aula.getIdCurso()+
                " and numero_aula = "+aula.getNumeroAula()+
                " and di.semestre = " + semestre+
                " and periodo = '"+aula.getPeriodo()+"'"+
                " and dia_semana = '" + aula.getDiaSemana()+"'";
        System.out.println(sql);
        List<Aula> aulas = new ArrayList<>();

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql);
                 ResultSet result = stmt.executeQuery()) {

                while (result.next()) {
                    Aula aulaa = new Aula(
                            result.getInt("idaula"),
                            result.getInt("id_professor"),
                            result.getInt("id_disciplina"),
                            result.getInt("id_curso"),
                            result.getString("dia_semana"),
                            result.getInt("numero_aula"),
                            "",
                            result.getString("periodo")
                    );

                    aulas.add(aulaa);
                }
                System.out.println("aulas: "+ aulas);
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar aulas: " + e.getMessage());
        }

        return aulas;
    }



    public List<Aula> listarAulasGrade(int id_curso, int semestre, String periodo) throws SQLException {

        String sql = "SELECT * FROM aula au " +
                "INNER JOIN disciplina di on di.id_disciplina = au.id_disciplina AND di.semestre = " + semestre +
                " WHERE " +
                " au.id_curso = "+id_curso+
                " AND periodo = '"+periodo+"'" ;
        System.out.println(sql);
        List<Aula> aulas = new ArrayList<>();

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql);
                 ResultSet result = stmt.executeQuery()) {

                while (result.next()) {
                    Aula aula = new Aula(
                            result.getInt("idaula"),
                            result.getInt("id_professor"),
                            result.getInt("id_disciplina"),
                            result.getInt("id_curso"),
                            result.getString("dia_semana"),
                            result.getInt("numero_aula"),
                            result.getString("nome"),
                            result.getString("periodo")
                    );

                    aulas.add(aula);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar aulas: " + e.getMessage());
        }

        return aulas;
    }

    public List<Integer> buscarProfessoresPorCursoESemestre(Aula aula, int semestre) throws SQLException {

        CursoDAO cursodao = new CursoDAO();
        Curso curso =  cursodao.buscarPorId(aula.getIdCurso());

        String sql = "SELECT DISTINCT prof.id_professor, prof.nome " +
                "FROM professor prof " +
                "LEFT JOIN disciplina disc ON disc.id_professor = prof.id_professor " +
                "WHERE disc.id_curso = " + aula.getIdCurso() + " " +
                "AND disc.semestre = " + semestre;

        System.out.println("SQL: " + sql);
        List<Integer> professores = new ArrayList<>();

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql);
                 ResultSet result = stmt.executeQuery()) {

                while (result.next()) {
                    int idProfessor = result.getInt("id_professor");
                    professores.add(idProfessor);
                }

                System.out.println("Professores encontrados: " + professores);
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar professores: " + e.getMessage());
        }

        return professores;
    }


    public List<Integer> buscarProfessoresOcupados(Aula aula, List<Integer> professores) throws SQLException {

        String ids = professores.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        String sql = "SELECT * FROM aula " +
                "inner join professor prof on aula.id_professor = prof.id_professor " +
                "WHERE aula.id_professor  in( " + ids + ") " +
                "and dia_semana ='" + aula.getDiaSemana()+
                "' and numero_aula = "+aula.getNumeroAula();

        System.out.println("SQL: " + sql);
        List<Integer> Ocupados = new ArrayList<>();

        try (Connection con = Conexao.conectar()) {
            assert con != null;
            try (PreparedStatement stmt = con.prepareStatement(sql);
                 ResultSet result = stmt.executeQuery()) {

                while (result.next()) {
                    int idProfessor = result.getInt("id_professor");
                    Ocupados.add(idProfessor);
                }

                System.out.println("Professores encontrados: " + Ocupados);
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar professores: " + e.getMessage());
        }

        return Ocupados;
    }

}
