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
@Tag(name = "CRUD Pessoas", description = "This controller manages operations with the people(Pessoas) table.")
public class Controller {

    @Autowired
    private Servico servico;

    @PostMapping("/api/register")
    @Operation(summary = "Route responsible for registering people")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person successfully registered", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))
            }),

            @ApiResponse(responseCode = "400", description = "Invalid Data", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Mensagem.class))
            })
    })
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa obj) {
        return servico.cadastrar(obj);
    }

    @GetMapping("/api/list")
    @Operation(summary = "Route responsible for listing people")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))
            })
    })
    public ResponseEntity<?> selecionar() {
        return servico.selecionar();
    }

    @GetMapping("/api/select/{codigo}")
    @Operation(summary = "Route responsible for listing a specific person by Code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))
            }),

            @ApiResponse(responseCode = "400", description = "Invalid Data or not founded", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Mensagem.class))
            })
    })
    public ResponseEntity<?> selecionarPeloCodigo(@PathVariable int codigo) {
        return servico.selecionarPeloCodigo(codigo);
    }

    @PutMapping("/api/update")
    @Operation(summary = "Route responsible for updating data for a specific person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))
            }),

            @ApiResponse(responseCode = "400", description = "Invalid Data or not founded", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Mensagem.class))
            })
    })
    public ResponseEntity<?> editar(@RequestBody Pessoa obj) {
        return servico.editar(obj);
    }

    @DeleteMapping("/api/delete/{codigo}")
    @Operation(summary = "Route responsible for removing a specific person by Code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))
            }),

            @ApiResponse(responseCode = "400", description = "Invalid Data ou Pessoa não encontrada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Mensagem.class))
            })
    })
    public ResponseEntity<?> remover(@PathVariable int codigo) {
        return servico.remover(codigo);
    }

}
