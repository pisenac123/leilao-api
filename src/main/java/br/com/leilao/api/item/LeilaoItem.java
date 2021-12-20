package br.com.leilao.api.item;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("leilaoItems")
public class LeilaoItem {

    @Id
    private String id;
    private String nome;
    private Double valorInicial;
    private Date dataAbertura;
    private String usuario;
    private List<LanceItem> lances;

}
