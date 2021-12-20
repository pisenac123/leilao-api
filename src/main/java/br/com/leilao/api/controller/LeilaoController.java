package br.com.leilao.api.controller;

import br.com.leilao.api.item.LeilaoItem;
import br.com.leilao.api.request.LanceRequest;
import br.com.leilao.api.request.LeilaoRequest;
import br.com.leilao.api.response.LeilaoResponse;
import br.com.leilao.api.service.LeilaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LeilaoController {

    @Autowired
    LeilaoService leilaoService;

    @PostMapping("/")
    public ResponseEntity<LeilaoResponse> criarLeilao(@Valid @RequestBody LeilaoRequest leilao) {
        try {
            LeilaoResponse leilaoResp = leilaoService.criarLeilao(leilao);
            return ResponseEntity.status(HttpStatus.CREATED).body(leilaoResp);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(LeilaoResponse.builder().isError(true).mensagem("Erro ao criar um leilão").build());
        }
    }

    @PostMapping("/lance")
    public ResponseEntity<LeilaoResponse> criarLance(@Valid @RequestBody LanceRequest lance) {
        try {
            LeilaoResponse leilaoResp = leilaoService.criarLance(lance);
            return ResponseEntity.status(HttpStatus.CREATED).body(leilaoResp);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(LeilaoResponse.builder().isError(true).mensagem("Erro ao realizar o lance leilão.").build());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeilaoItem> atualizarLeilao(@PathVariable String id, @Valid @RequestBody LeilaoRequest leilao) {
        try {
            LeilaoItem leilaoAtualizado = leilaoService.atualizarLeilao(id, leilao);
            return ResponseEntity.ok().body(leilaoAtualizado);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(LeilaoItem.builder().build());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<LeilaoItem> buscarLeilaoPeloId(@PathVariable String id) {
        try {
            LeilaoItem item = leilaoService.buscarLeilaoPeloId(id);
            return ResponseEntity.ok().body(item);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(LeilaoItem.builder().build());
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<LeilaoItem>> buscarLeiloes() {

        List<LeilaoItem> listaLeiloes = new ArrayList<LeilaoItem>();

        try {
            listaLeiloes = leilaoService.buscarLeiloes();
            return ResponseEntity.ok().body(listaLeiloes);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaLeiloes);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LeilaoResponse> deletarLeilaoPeloId(@PathVariable String id) {
        try {
            LeilaoResponse leilaoResp = leilaoService.deletarLeilao(id);
            return ResponseEntity.ok().body(leilaoResp);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(LeilaoResponse.builder().isError(true).mensagem("Erro ao deletar o leilão.").build());
        }
    }
}
