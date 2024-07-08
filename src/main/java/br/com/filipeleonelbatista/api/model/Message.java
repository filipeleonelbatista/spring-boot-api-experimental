package br.com.filipeleonelbatista.api.model;

import org.springframework.stereotype.Component;

@Component
public class Message {
  private String message;

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
