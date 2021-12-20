package br.com.leilao.api.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LeilaoRequest {

    @NotEmpty(message = "O nome do leilão não pode ser inválido.")
    private String nome;

    @NotNull(message = "O valor inicial não pode ser vazio ou menor que 1.")
    private Double valorInicial;

    @NotEmpty(message = "O nome do usuário não pode ser inválido.")
    private String usuario;
}
