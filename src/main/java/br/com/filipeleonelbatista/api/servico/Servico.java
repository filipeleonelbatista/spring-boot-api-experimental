package br.com.filipeleonelbatista.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.filipeleonelbatista.api.model.Mensagem;
import br.com.filipeleonelbatista.api.model.Pessoa;
import br.com.filipeleonelbatista.api.repositorio.Repositorio;

@Service
public class Servico {
  @Autowired
  private Mensagem mensagem;

  @Autowired
  private Repositorio repo;

  public ResponseEntity<?> cadastrar(Pessoa obj) {
    if (obj.getNome().equals("")) {
      mensagem.setMensagem("Name needs to be filled in");
      return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    } else if (obj.getIdade() < 0) {
      mensagem.setMensagem("Enter a valid age");
      return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<>(repo.save(obj), HttpStatus.CREATED);
    }
  }

  public ResponseEntity<?> selecionar() {
    return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
  }

  public ResponseEntity<?> selecionarPeloCodigo(int codigo) {
    if (repo.countByCodigo(codigo) == 0) {
      mensagem.setMensagem("No person found");
      return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<>(repo.findByCodigo(codigo), HttpStatus.OK);
    }
  }

  public ResponseEntity<?> editar(Pessoa obj) {
    if (repo.countByCodigo(obj.getCodigo()) == 0) {
      mensagem.setMensagem("The code provided does not exist.");
      return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    } else if (obj.getNome().equals("")) {
      mensagem.setMensagem("You must provide a name.");
      return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    } else if (obj.getIdade() < 0) {
      mensagem.setMensagem("Enter a valid age");
      return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<>(repo.save(obj), HttpStatus.OK);
    }
  }

  public ResponseEntity<?> remover(int codigo) {
    if (repo.countByCodigo(codigo) == 0) {
      mensagem.setMensagem("No person found");
      return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    } else {
      Pessoa obj = repo.findByCodigo(codigo);
      repo.delete(obj);
      mensagem.setMensagem("Person removed successfully!");
      return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }
  }

}
