package br.com.leilao.api.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LanceRequest {

    @NotEmpty(message = "O id do leilão não pode ser inválido.")
    private String idLeilao;

    @NotEmpty(message = "O nome do usuário não pode ser inválido.")
    private String usuario;

    @NotNull(message = "O valor do lance não pode ser vazio ou menor que 1.")
    private Double valorLance;
}
