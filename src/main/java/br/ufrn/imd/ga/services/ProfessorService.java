package br.ufrn.imd.ga.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;

import br.ufrn.imd.ga.model.Horario;
import br.ufrn.imd.ga.model.Pessoa;

public class ProfessorService {
	private static ArrayList<Pessoa> professores = new ArrayList<Pessoa>();

	public List<Pessoa> listarProfessores() {
		return professores;
	}

	public Pessoa getProfessorByCpf(String cpf) {
		for (Pessoa p: professores) {
			if(p.getCPF().equals(cpf)) return p;
		}
		return null;
	}

	public Pessoa addProfessor(Pessoa professor) {
		if(getProfessorByCpf(professor.getCPF()) != null ) return null;
		professor.setTipo(2);
		professores.add(professor);
		return professores.get(professores.size() -1);
	}
	public Pessoa updateProfessor(String cpf, Pessoa professor) {
		int index = professores.indexOf(professor);
		if(index < 0) return null;
		professor.setTipo(2);
		professores.set(index, professor);
		return professores.get(index);
	}

	public boolean deleteProfessor(String cpf) {
		Pessoa professor = getProfessorByCpf(cpf);
		if(professor == null) return false;
		
		int index = professores.indexOf(professor);
		professores.remove(index);
		return true;
	}
	
}
