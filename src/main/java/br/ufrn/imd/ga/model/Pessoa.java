package br.ufrn.imd.ga.model;

import java.util.Objects;

public class Pessoa {
    private String CPF;
    private String nome;
    private int tipo; // 1 = ALUNO; 2 = PROFESSOR.
    private String disciplina;
    private Horario horario;

    public Pessoa(String CPF, String nome,String disciplina, Horario horario) {
        this.CPF = CPF;
        this.nome = nome;
        this.disciplina = disciplina;
        this.horario = horario;
    }
    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

	@Override
	public String toString() {
		String pessoa = tipo == 1? "Aluno" :"Professor";
		return pessoa + " [CPF=" + CPF + ", nome=" + nome + ", disciplina=" + disciplina + ", horario="
				+ horario + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(CPF, disciplina, horario, nome, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(CPF, other.CPF) ;
	}
}
