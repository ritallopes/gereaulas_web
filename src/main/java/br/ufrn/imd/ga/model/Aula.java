package br.ufrn.imd.ga.model;

import java.util.Objects;

import br.ufrn.imd.ga.services.AlunoService;
import br.ufrn.imd.ga.services.ProfessorService;

public class Aula {
	private int id;;
    private String disciplina;
    private Pessoa professor;
    private Pessoa aluno;
    private Horario horario;
    

    public Aula(String disciplina, String cpfProfessor, String cpfAluno, Horario horario) {
		super();
		ProfessorService profServ = new ProfessorService();
		AlunoService alunoServ = new AlunoService();
		this.disciplina = disciplina;
		
		this.professor = profServ.getProfessorByCpf(cpfProfessor);
		this.aluno = alunoServ.getAlunoByCpf(cpfAluno);
		this.horario = horario;
		this.id = this.hashCode();
	}

	@Override
	public int hashCode() {
		return Math.abs(Objects.hash(aluno, disciplina,id, professor));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		return Objects.equals(aluno, other.aluno) && Objects.equals(disciplina, other.disciplina)
				&& Objects.equals(horario, other.horario) && id == other.id &&
				Objects.equals(professor, other.professor);
	}

	public boolean equals(int id) {
		return this.id == id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

	public Pessoa getProfessor() {
		return professor;
	}

	public void setProfessor(Pessoa professor) {
		this.professor = professor;
	}

	public Pessoa getAluno() {
		return aluno;
	}

	public void setAluno(Pessoa aluno) {
		this.aluno = aluno;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	

}
