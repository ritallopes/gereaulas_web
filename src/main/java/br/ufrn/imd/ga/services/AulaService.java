package br.ufrn.imd.ga.services;

import java.util.ArrayList;
import java.util.List;


import br.ufrn.imd.ga.model.Aula;
import br.ufrn.imd.ga.model.Horario;
import br.ufrn.imd.ga.model.Pessoa;

public class AulaService {
	private static ArrayList<Aula> aulas = new ArrayList<Aula>();

	public List<Aula> listarAulas() {
		return aulas;
	}

	public Aula getAulaById(int i) {
		for (Aula a: aulas) {
			if(a.equals(i)) return a;
		}
		return null;
	}
	public List<Aula> getAulasByProfessor(String cpfProfessor) {
		ArrayList<Aula> aulasProfessor= new ArrayList<Aula>();
		for (Aula a: aulas) {
			if(a.getProfessor().getCPF().equals(cpfProfessor)) aulasProfessor.add(a);
		}
		return aulasProfessor;
	}
	public List<Aula> getAulasByAluno(String cpfAluno) {
		ArrayList<Aula> aulasAluno = new ArrayList<Aula>();
		for (Aula a: aulas) {
			if(a.getAluno().getCPF().equals(cpfAluno)) aulasAluno.add(a);
		}
		return aulasAluno;
	}

	public Aula addAula(Aula aula) {
		if(getAulaById(aula.getId()) != null ) return null;
		if(verificarConflito(aula.getId(), aula)) return null;
		aulas.add(aula);
		return aulas.get(aulas.size() -1);
	}
	private Aula findAulaByProfAlunoHor(Pessoa professor, Pessoa aluno, Horario horario) {
		for (Aula a: aulas) {
			if( a.getHorario().equals(horario) && (a.getAluno().equals(aluno) || a.getProfessor().equals(professor))) return a;
		}
		return null;
	}
	private boolean verificarConflito(int id, Aula aula) {
		if(aula.getAluno() == null || aula.getProfessor() == null) return true;
		if((findAulaByProfAlunoHor(aula.getAluno(), aula.getProfessor(), aula.getHorario()) != null) && getAulaById(id) == null) return true;
		return false;
	}
	public Aula updateAula(int id, Aula aula) {
		if(verificarConflito(id, aula)) return null;
		Aula aulaInArray = getAulaById(id);
		int index = aulas.indexOf(aulaInArray);
		if(index < 0) return null;
		aulas.set(index, aula);
		return aulas.get(index);
	}

	public boolean deleteAula(int id) {
		Aula aula = getAulaById(id);
		if(aula == null) return false;
		
		int index = aulas.indexOf(aula);
		aulas.remove(index);
		return true;
	}
	
}
