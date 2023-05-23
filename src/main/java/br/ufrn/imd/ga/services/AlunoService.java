package br.ufrn.imd.ga.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;

import br.ufrn.imd.ga.model.Horario;
import br.ufrn.imd.ga.model.Pessoa;

public class AlunoService {
	private static ArrayList<Pessoa> alunos = new ArrayList<Pessoa>();

	public List<Pessoa> listarAlunos() {
		return alunos;
	}

	public Pessoa getAlunoByCpf(String cpf) {
		for (Pessoa a: alunos) {
			if(a.getCPF().equals(cpf)) return a;
		}
		return null;
	}

	public Pessoa addAluno(Pessoa aluno) {
		if(getAlunoByCpf(aluno.getCPF()) != null ) return null;
		alunos.add(aluno);
		return alunos.get(alunos.size() -1);
	}
	public Pessoa updateAluno(String cpf, Pessoa aluno) {
		int index = alunos.indexOf(aluno);
		if(index < 0) return null;
		alunos.set(index, aluno);
		return alunos.get(index);
	}

	public boolean deleteAluno(String cpf) {
		Pessoa aluno = getAlunoByCpf(cpf);
		if(aluno == null) return false;
		
		int index = alunos.indexOf(aluno);
		alunos.remove(index);
		return true;
	}
	
}
