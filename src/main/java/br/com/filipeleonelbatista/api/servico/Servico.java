package br.com.filipeleonelbatista.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.filipeleonelbatista.api.model.Message;
import br.com.filipeleonelbatista.api.model.Person;
import br.com.filipeleonelbatista.api.repo.Repo;

@Service
public class Servico {
  @Autowired
  private Message message;

  @Autowired
  private Repo repo;

  public ResponseEntity<?> register(Person obj) {
    if (obj.getName().equals("")) {
      message.setMessage("Name needs to be filled in");
      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    } else if (obj.getAge() < 0) {
      message.setMessage("Enter a valid age");
      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<>(repo.save(obj), HttpStatus.CREATED);
    }
  }

  public ResponseEntity<?> select() {
    return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
  }

  public ResponseEntity<?> selectById(int id) {
    if (repo.countById(id) == 0) {
      message.setMessage("No person found");
      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<>(repo.findById(id), HttpStatus.OK);
    }
  }

  public ResponseEntity<?> update(Person obj) {
    if (repo.countById(obj.getId()) == 0) {
      message.setMessage("The code provided does not exist.");
      return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    } else if (obj.getName().equals("")) {
      message.setMessage("You must provide a name.");
      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    } else if (obj.getAge() < 0) {
      message.setMessage("Enter a valid age");
      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<>(repo.save(obj), HttpStatus.OK);
    }
  }

  public ResponseEntity<?> delete(int id) {
    if (repo.countById(id) == 0) {
      message.setMessage("No person found");
      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    } else {
      Person obj = repo.findById(id);
      repo.delete(obj);
      message.setMessage("Person removed successfully!");
      return new ResponseEntity<>(message, HttpStatus.OK);
    }
  }

}
