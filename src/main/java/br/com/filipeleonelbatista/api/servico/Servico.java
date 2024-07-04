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
      mensagem.setMensagem("O nome precisa ser preenchido");
      return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    } else if (obj.getIdade() < 0) {
      mensagem.setMensagem("Informe uma idade válida");
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
      mensagem.setMensagem("Não foi encontrada nenhuma pessoa");
      return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<>(repo.findByCodigo(codigo), HttpStatus.OK);
    }
  }

  public ResponseEntity<?> editar(Pessoa obj) {
    if (repo.countByCodigo(obj.getCodigo()) == 0) {
      mensagem.setMensagem("O código informado não existe.");
      return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    } else if (obj.getNome().equals("")) {
      mensagem.setMensagem("É necessário informar um nome");
      return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    } else if (obj.getIdade() < 0) {
      mensagem.setMensagem("Informe uma idade válida");
      return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<>(repo.save(obj), HttpStatus.OK);
    }
  }

  public ResponseEntity<?> remover(int codigo) {
    if (repo.countByCodigo(codigo) == 0) {
      mensagem.setMensagem("Não foi encontrada nenhuma pessoa");
      return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    } else {
      Pessoa obj = repo.findByCodigo(codigo);
      repo.delete(obj);
      mensagem.setMensagem("Pessoa removida com sucesso!");
      return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }
  }

}
