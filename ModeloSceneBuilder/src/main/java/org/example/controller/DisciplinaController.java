package org.example.controller;

import org.example.classes.Disciplina;
import org.example.dao.CursoDAO;
import org.example.dao.DisciplinaDAO;
import org.example.dao.ProfessorDAO;

public class DisciplinaController {
    private DisciplinaDAO dao = new DisciplinaDAO();
    private ProfessorDAO professorDAO = new ProfessorDAO();
    private CursoDAO cursoDAO = new CursoDAO();

    public void cadastrarDisciplina(String nome, int carga_horaria, long id_professor, long id_curso) {
        Professor professor = professorDAO.buscarPorId(id_professor);
        Curso curso = cursoDAO.buscarPorId(id_curso);

        if (professor == null || curso == null) {
            throw new IllegalArgumentException("Professor ou curso não encontrado.");
        }

        Disciplina disciplina = new Disciplina(nome, carga_horaria, professor, curso);
    }
    }

