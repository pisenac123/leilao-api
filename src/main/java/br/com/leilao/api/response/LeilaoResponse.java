package br.com.leilao.api.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LeilaoResponse {
    private boolean isError;
    private String mensagem;
}
