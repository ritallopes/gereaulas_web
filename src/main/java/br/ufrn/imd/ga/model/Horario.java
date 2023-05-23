package br.ufrn.imd.ga.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Horario {
    private LocalDateTime inicio; 
    private LocalDateTime fim; 

    public Horario(LocalDateTime inicio, LocalDateTime fim) {
    	if(inicio.isBefore(fim)) {
    		this.fim = inicio;
    	    this.inicio = fim;
    	}
        this.inicio = inicio;
        this.fim = fim;
    }
    

	@Override
	public int hashCode() {
		return Objects.hash(fim, inicio);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Horario other = (Horario) obj;
		return Objects.equals(fim, other.fim) && Objects.equals(inicio, other.inicio);
	}


	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getFim() {
		return fim;
	}

	public void setFim(LocalDateTime fim) {
		this.fim = fim;
	}
}

