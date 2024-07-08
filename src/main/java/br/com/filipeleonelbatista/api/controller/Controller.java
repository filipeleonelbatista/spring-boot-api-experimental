package br.com.filipeleonelbatista.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.filipeleonelbatista.api.model.Mensagem;
import br.com.filipeleonelbatista.api.model.Pessoa;
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

    @Autowired
    private Servico servico;

    @PostMapping("/api/cadastrar")
    @Operation(summary = "Rota responsável pelo cadastro de pessoas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa cadastrada com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))
            }),

            @ApiResponse(responseCode = "400", description = "Informação inválida", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Mensagem.class))
            })
    })
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa obj) {
        return servico.cadastrar(obj);
    }

    @GetMapping("/api/listar")
    @Operation(summary = "Rota responsável pela listagem de pessoas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))
            })
    })
    public ResponseEntity<?> selecionar() {
        return servico.selecionar();
    }

    @GetMapping("/api/selecionar/{codigo}")
    @Operation(summary = "Rota responsável pela listagem de uma pessoa especifica por Código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))
            }),

            @ApiResponse(responseCode = "400", description = "Informação inválida ou Pessoa não encontrada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Mensagem.class))
            })
    })
    public ResponseEntity<?> selecionarPeloCodigo(@PathVariable int codigo) {
        return servico.selecionarPeloCodigo(codigo);
    }

    @PutMapping("/api/atualizar")
    @Operation(summary = "Rota responsável pela atualização de dados de uma pessoa especifica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))
            }),

            @ApiResponse(responseCode = "400", description = "Informação inválida ou Pessoa não encontrada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Mensagem.class))
            })
    })
    public ResponseEntity<?> editar(@RequestBody Pessoa obj) {
        return servico.editar(obj);
    }

    @DeleteMapping("/api/deletar/{codigo}")
    @Operation(summary = "Rota responsável por remover uma pessoa especifica por Código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))
            }),

            @ApiResponse(responseCode = "400", description = "Informação inválida ou Pessoa não encontrada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Mensagem.class))
            })
    })
    public ResponseEntity<?> remover(@PathVariable int codigo) {
        return servico.remover(codigo);
    }

}
