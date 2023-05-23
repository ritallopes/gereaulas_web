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

import br.ufrn.imd.ga.services.AulaService;
import br.ufrn.imd.ga.model.Aula;

@RestController
@RequestMapping("/aulas")
public class AulaController {
	private AulaService service = new AulaService();
	
	@GetMapping
	public ResponseEntity<List<Aula>> listar() {
		return ResponseEntity.ok(service.listarAulas());
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Aula> getAulaById(@PathVariable int id) {
		Aula aula = service.getAulaById(id);
		if(!(aula == null)){
			return ResponseEntity.ok().body(aula);
		}
		return ResponseEntity.notFound().build();
	}
	@GetMapping("/aluno/{cpf}")
	@ResponseBody
	public ResponseEntity<List<Aula>> getAulaByAluno(@PathVariable String cpf) {
		List<Aula> aulas = service.getAulasByAluno(cpf);
		if(!(aulas.isEmpty())){
			return ResponseEntity.ok().body(aulas);
		}
		return ResponseEntity.notFound().build();
	}
	@GetMapping("/professor/{cpf}")
	@ResponseBody
	public ResponseEntity<List<Aula>> getAulaByProfessor(@PathVariable String cpf) {
		List<Aula> aulas = service.getAulasByProfessor(cpf);
		if(!(aulas.isEmpty())){
			return ResponseEntity.ok().body(aulas);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Aula> addAula(@RequestBody Aula aula) {
		if(aula == null) return ResponseEntity.unprocessableEntity().body(aula);
		Aula newAula = service.addAula(aula);
		if(newAula == null ) return new ResponseEntity(HttpStatus.CONFLICT);
		return ResponseEntity.ok().body(newAula);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Aula> updateAula(@PathVariable int id, @RequestBody Aula aula) {
		if(aula == null) return ResponseEntity.unprocessableEntity().body(aula);

		Aula aulaUpdate = service.updateAula(id, aula);
		
		if(aulaUpdate == null) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(aulaUpdate);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteAula(@PathVariable int id) {
		boolean isDeleted = service.deleteAula(id);
		if(isDeleted) return ResponseEntity.ok().build();
		return ResponseEntity.notFound().build();
	}
}
