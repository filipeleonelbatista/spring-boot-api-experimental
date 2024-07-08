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

import br.com.filipeleonelbatista.api.model.Message;
import br.com.filipeleonelbatista.api.model.Person;
import br.com.filipeleonelbatista.api.servico.Servico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "CRUD Persons", description = "This controller manages operations with the people(Persons) table.")
public class Controller {

    @Autowired
    private Servico servico;

    @PostMapping("/api/register")
    @Operation(summary = "Route responsible for registering people")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person successfully registered", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Person.class))
            }),

            @ApiResponse(responseCode = "400", description = "Invalid Data", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Message.class))
            })
    })
    public ResponseEntity<?> register(@RequestBody Person obj) {
        return servico.register(obj);
    }

    @GetMapping("/api/list")
    @Operation(summary = "Route responsible for listing people")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Person.class))
            })
    })
    public ResponseEntity<?> select() {
        return servico.select();
    }

    @GetMapping("/api/select/{id}")
    @Operation(summary = "Route responsible for listing a specific person by Code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Person.class))
            }),

            @ApiResponse(responseCode = "400", description = "Invalid Data or not founded", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Message.class))
            })
    })
    public ResponseEntity<?> selectById(@PathVariable int id) {
        return servico.selectById(id);
    }

    @PutMapping("/api/update")
    @Operation(summary = "Route responsible for updating data for a specific person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Person.class))
            }),

            @ApiResponse(responseCode = "400", description = "Invalid Data or not founded", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Message.class))
            })
    })
    public ResponseEntity<?> update(@RequestBody Person obj) {
        return servico.update(obj);
    }

    @DeleteMapping("/api/delete/{id}")
    @Operation(summary = "Route responsible for removing a specific person by Code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Person.class))
            }),

            @ApiResponse(responseCode = "400", description = "Invalid Data ou Person não encontrada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Message.class))
            })
    })
    public ResponseEntity<?> delete(@PathVariable int id) {
        return servico.delete(id);
    }

}
