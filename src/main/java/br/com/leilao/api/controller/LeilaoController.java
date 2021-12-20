package br.com.leilao.api.controller;

import br.com.leilao.api.item.LeilaoItem;
import br.com.leilao.api.request.LanceRequest;
import br.com.leilao.api.request.LeilaoRequest;
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
    public ResponseEntity<String> criarLeilao(@Valid @RequestBody LeilaoRequest leilao) {

        try {
            leilaoService.criarLeilao(leilao);
            return ResponseEntity.status(HttpStatus.CREATED).body("Leilao criado com sucesso.");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar leilão.");
        }
    }

    @PostMapping("/lance")
    public ResponseEntity<String> criarLance(@Valid @RequestBody LanceRequest lance) {
        try {
            leilaoService.criarLance(lance);
            return ResponseEntity.status(HttpStatus.CREATED).body("Lance realizado com sucesso.");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao realizar o lance leilão.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarLeilao(@PathVariable String id, @Valid @RequestBody LeilaoRequest leilao) {
        try {
            leilaoService.atualizarLeilao(id, leilao);
            return ResponseEntity.status(HttpStatus.CREATED).body("Leilao atualizado com sucesso.");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar o leilão.");
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
    public ResponseEntity<String> deletarLeilaoPeloId(@PathVariable String id) {
        try {
            leilaoService.deletarLeilao(id);
            return ResponseEntity.ok().body("Leilao deletado com sucesso.");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar o leilão.");
        }
    }
}
