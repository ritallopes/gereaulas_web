package br.ufrn.imd.ga.controller;

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

import br.ufrn.imd.ga.services.AlunoService;
import br.ufrn.imd.ga.model.Pessoa;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
	private AlunoService service = new AlunoService();
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> listar() {
		return ResponseEntity.ok(service.listarAlunos());
	}
	
	@GetMapping("/{cpf}")
	@ResponseBody
	public ResponseEntity<Pessoa> getAlunoByCpf(@PathVariable String cpf) {
		Pessoa aluno = service.getAlunoByCpf(cpf);
		if(!(aluno == null)){
			return ResponseEntity.ok().body(aluno);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Pessoa> addAluno(@RequestBody Pessoa aluno) {
		if(aluno == null) return ResponseEntity.unprocessableEntity().body(aluno);
		Pessoa newAluno = service.addAluno(aluno);
		if(newAluno == null ) return new ResponseEntity(HttpStatus.CONFLICT);
		newAluno.setTipo(1);
		
		return ResponseEntity.ok().body(newAluno);
	}
	
	@PutMapping("/{cpf}")
	public ResponseEntity<Pessoa> updateAluno(@PathVariable String cpf, @RequestBody Pessoa aluno) {
		if(aluno == null) return ResponseEntity.unprocessableEntity().body(aluno);

		Pessoa alunoUpdate = service.updateAluno(cpf, aluno);
		
		if(alunoUpdate == null) return ResponseEntity.notFound().build();
		alunoUpdate.setTipo(1);

		return ResponseEntity.ok().body(alunoUpdate);
	}
	
	
	@DeleteMapping("/{cpf}")
	public ResponseEntity<Object> deleteAluno(@PathVariable String cpf) {
		boolean isDeleted = service.deleteAluno(cpf);
		if(isDeleted) return ResponseEntity.ok().build();
		return ResponseEntity.notFound().build();
	}
}
