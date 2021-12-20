package br.com.leilao.api.item;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanceItem {
    private String usuario;
    private Double valorLance;
    private Date dataLance;
}
