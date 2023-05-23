package br.ufrn.imd.ga.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.ga.services.ProfessorService;
import br.ufrn.imd.ga.model.Pessoa;

@RestController
@RequestMapping("/professores")
public class ProfessorController {
	private ProfessorService service = new ProfessorService();
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> listar() {
		return ResponseEntity.ok(service.listarProfessores());
	}
	
	@GetMapping("/{cpf}")
	@ResponseBody
	public ResponseEntity<Pessoa> getProfessorByCpf(@PathVariable String cpf) {
		Pessoa professor = service.getProfessorByCpf(cpf);
		if(!(professor == null)){
			return ResponseEntity.ok().body(professor);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Pessoa> addProfessor(@RequestBody Pessoa professor) {
		if(professor == null) return ResponseEntity.unprocessableEntity().body(professor);
		Pessoa newProfessor = service.addProfessor(professor);
		if(newProfessor == null ) return new ResponseEntity(HttpStatus.CONFLICT);
		return ResponseEntity.ok().body(newProfessor);
	}
	
	@PutMapping("/{cpf}")
	public ResponseEntity<Pessoa> updateProfessor(@PathVariable String cpf, @RequestBody Pessoa professor) {
		if(professor == null) return ResponseEntity.unprocessableEntity().body(professor);

		Pessoa professorUpdate = service.updateProfessor(cpf, professor);
		
		if(professorUpdate == null) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(professorUpdate);
	}
	
	
	@DeleteMapping("/{cpf}")
	public ResponseEntity<Object> deleteProfessor(@PathVariable String cpf) {
		boolean isDeleted = service.deleteProfessor(cpf);
		if(isDeleted) return ResponseEntity.ok().build();
		return ResponseEntity.notFound().build();
	}
}
