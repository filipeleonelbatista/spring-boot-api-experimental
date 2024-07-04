package br.com.filipeleonelbatista.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.filipeleonelbatista.api.model.Cliente;
import br.com.filipeleonelbatista.api.model.Pessoa;
import br.com.filipeleonelbatista.api.repositorio.Repositorio;
import br.com.filipeleonelbatista.api.servico.Servico;

@RestController
public class Controller {

  @Autowired
  private Repositorio repo;

  @Autowired
  private Servico servico;

  // -------- INICIO DO CRUD -----------
  @PostMapping("/api")
  public ResponseEntity<?> cadastrar(@RequestBody Pessoa obj) {
    return servico.cadastrar(obj);
  }

  @GetMapping("/api")
  public ResponseEntity<?> selecionar() {
    return servico.selecionar();
  }

  @GetMapping("/api/{codigo}")
  public ResponseEntity<?> selecionarPeloCodigo(@PathVariable int codigo) {
    return servico.selecionarPeloCodigo(codigo);
  }

  @PutMapping("/api")
  public ResponseEntity<?> editar(@RequestBody Pessoa obj) {
    return servico.editar(obj);
  }

  @DeleteMapping("/api/{codigo}")
  public ResponseEntity<?> remover(@PathVariable int codigo) {
    return servico.remover(codigo);
  }

  // -------- FIM DO CRUD -----------

  @GetMapping("/api/contador")
  public long contador() {
    return repo.count();
  }

  @GetMapping("/api/ordenarNomes")
  public List<Pessoa> ordenarNomes() {
    return repo.findByOrderByNomeDesc();
  }

  @GetMapping("/api/ordenarNomes2")
  public List<Pessoa> ordenarNomes2() {
    return repo.findByNomeOrderByIdadeDesc("Filipe Leonel Batista");
  }

  @GetMapping("/api/nomeContem")
  public List<Pessoa> nomeContem() {
    return repo.findByNomeContaining("l");
  }

  @GetMapping("/api/iniciaCom")
  public List<Pessoa> iniciaCom() {
    return repo.findByNomeStartsWith("F");
  }

  @GetMapping("/api/terminaCom")
  public List<Pessoa> terminaCom() {
    return repo.findByNomeEndsWith("a");
  }

  @GetMapping("/api/somaIdades")
  public int somaIdades() {
    return repo.somaIdades();
  }

  @GetMapping("/api/idadeMaiorIgual")
  public List<Pessoa> idadeMaiorIgual() {
    return repo.idadeMaiorIgual(35);
  }

  @GetMapping("")
  public String message() {
    return "Hello World!";
  }

  @GetMapping("/boasVindas")
  public String welcome() {
    return "Seja bem vindo(a)";
  }

  @GetMapping("/boasVindas/{nome}")
  public String welcome(@PathVariable String nome) {
    return "Seja bem vindo(a) " + nome;
  }

  @PostMapping("/pessoa")
  public Pessoa pessoa(@RequestBody Pessoa p) {
    return p;
  }

  @GetMapping("/status")
  public ResponseEntity<?> status() {
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  // USANDO JPA VALIDATION
  @PostMapping("/cliente")
  public void cliente(@Valid @RequestBody Cliente obj) {
    return;
  }

}
