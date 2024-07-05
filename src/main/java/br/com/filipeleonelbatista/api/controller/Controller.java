package br.com.filipeleonelbatista.api.controller;

// import java.util.List;

// import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.filipeleonelbatista.api.model.Mensagem;
// import br.com.filipeleonelbatista.api.model.Cliente;
import br.com.filipeleonelbatista.api.model.Pessoa;
// import br.com.filipeleonelbatista.api.repositorio.Repositorio;
import br.com.filipeleonelbatista.api.servico.Servico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "CRUD Pessoas", description = "Este controlador gerencia operações com a tabela pessoas.")
public class Controller {

  // @Autowired
  // private Repositorio repo;

  @Autowired
  private Servico servico;

  // -------- INICIO DO CRUD -----------
  @PostMapping("/api/cadastrar")
  @Operation(summary = "Rota responsável pelo cadastro de pessoas")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "201", 
          description = "Pessoa cadastrada com sucesso",
          content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Pessoa.class)
              )
          }
      ),

      @ApiResponse(
          responseCode = "400", 
          description = "Informação inválida",
          content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Mensagem.class)
              )
          }
      )
  })
  public ResponseEntity<?> cadastrar(@RequestBody Pessoa obj) {
    return servico.cadastrar(obj);
  }

  @GetMapping("/api/listar")
  @Operation(summary = "Rota responsável pela listagem de pessoas")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200", 
          description = "Ok",
          content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Pessoa.class)
              )
          }
      )
  })
  public ResponseEntity<?> selecionar() {
    return servico.selecionar();
  }

  @GetMapping("/api/selecionar/{codigo}")
  @Operation(summary = "Rota responsável pela listagem de uma pessoa especifica por Código")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200", 
          description = "Ok",
          content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Pessoa.class)
              )
          }
      ),

      @ApiResponse(
          responseCode = "400", 
          description = "Informação inválida ou Pessoa não encontrada",
          content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Mensagem.class)
              )
          }
      )
  })
  public ResponseEntity<?> selecionarPeloCodigo(@PathVariable int codigo) {
    return servico.selecionarPeloCodigo(codigo);
  }

  @PutMapping("/api/atualizar")
  @Operation(summary = "Rota responsável pela atualização de dados de uma pessoa especifica")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200", 
          description = "Ok",
          content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Pessoa.class)
              )
          }
      ),

      @ApiResponse(
          responseCode = "400", 
          description = "Informação inválida ou Pessoa não encontrada",
          content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Mensagem.class)
              )
          }
      )
  })
  public ResponseEntity<?> editar(@RequestBody Pessoa obj) {
    return servico.editar(obj);
  }

  @DeleteMapping("/api/deletar/{codigo}")  
  @Operation(summary = "Rota responsável por remover uma pessoa especifica por Código")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200", 
          description = "Ok",
          content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Pessoa.class)
              )
          }
      ),

      @ApiResponse(
          responseCode = "400", 
          description = "Informação inválida ou Pessoa não encontrada",
          content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Mensagem.class)
              )
          }
      )
  })
  public ResponseEntity<?> remover(@PathVariable int codigo) {
    return servico.remover(codigo);
  }

  // -------- FIM DO CRUD -----------

  // @GetMapping("/api/contador")
  // public long contador() {
  // return repo.count();
  // }

  // @GetMapping("/api/ordenarNomes")
  // public List<Pessoa> ordenarNomes() {
  // return repo.findByOrderByNomeDesc();
  // }

  // @GetMapping("/api/ordenarNomes2")
  // public List<Pessoa> ordenarNomes2() {
  // return repo.findByNomeOrderByIdadeDesc("Filipe Leonel Batista");
  // }

  // @GetMapping("/api/nomeContem")
  // public List<Pessoa> nomeContem() {
  // return repo.findByNomeContaining("l");
  // }

  // @GetMapping("/api/iniciaCom")
  // public List<Pessoa> iniciaCom() {
  // return repo.findByNomeStartsWith("F");
  // }

  // @GetMapping("/api/terminaCom")
  // public List<Pessoa> terminaCom() {
  // return repo.findByNomeEndsWith("a");
  // }

  // @GetMapping("/api/somaIdades")
  // public int somaIdades() {
  // return repo.somaIdades();
  // }

  // @GetMapping("/api/idadeMaiorIgual")
  // public List<Pessoa> idadeMaiorIgual() {
  // return repo.idadeMaiorIgual(35);
  // }

  // @GetMapping("")
  // public String message() {
  // return "Hello World!";
  // }

  // @GetMapping("/boasVindas")
  // public String welcome() {
  // return "Seja bem vindo(a)";
  // }

  // @GetMapping("/boasVindas/{nome}")
  // public String welcome(@PathVariable String nome) {
  // return "Seja bem vindo(a) " + nome;
  // }

  // @PostMapping("/pessoa")
  // public Pessoa pessoa(@RequestBody Pessoa p) {
  // return p;
  // }

  // @GetMapping("/status")
  // public ResponseEntity<?> status() {
  // return new ResponseEntity<>(HttpStatus.CREATED);
  // }

  // // USANDO JPA VALIDATION
  // @PostMapping("/cliente")
  // public void cliente(@Valid @RequestBody Cliente obj) {
  // return;
  // }

}
