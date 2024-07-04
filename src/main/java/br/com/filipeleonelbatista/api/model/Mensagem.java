package br.com.filipeleonelbatista.api.model;

import org.springframework.stereotype.Component;

@Component
public class Mensagem {
  private String mensagem;

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMensagem() {
    return mensagem;
  }
}
