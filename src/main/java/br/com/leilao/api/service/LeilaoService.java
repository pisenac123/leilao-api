package br.com.leilao.api.service;

import br.com.leilao.api.item.LanceItem;
import br.com.leilao.api.item.LeilaoItem;
import br.com.leilao.api.repository.ItemRepository;
import br.com.leilao.api.request.LanceRequest;
import br.com.leilao.api.request.LeilaoRequest;
import br.com.leilao.api.response.LeilaoResponse;
import br.com.leilao.api.utils.RandomArgsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LeilaoService {

    @Autowired
    ItemRepository leilaoItemRepo;

    public LeilaoResponse criarLeilao(LeilaoRequest novoLeilao) {

        String easy = RandomArgsUtils.digits + "ACEFGHJKLMNPQRUVWXYabcdefhijkprstuvwx";
        RandomArgsUtils randomDigits = new RandomArgsUtils(23, new SecureRandom(), easy);

        LeilaoItem itemExistente = leilaoItemRepo.findItemByNome(novoLeilao.getNome());

        if(itemExistente != null){
            return LeilaoResponse.builder().isError(true).mensagem("Já existem um leilão com esse nome. Por favor, tente um nome diferente.").build();
        }

        leilaoItemRepo.save(LeilaoItem.builder()
                .id(randomDigits.nextString())
                .nome(novoLeilao.getNome())
                .usuario(novoLeilao.getUsuario())
                .valorInicial(novoLeilao.getValorInicial())
                .dataAbertura(new Date())
                .build());

        return LeilaoResponse.builder().isError(false).mensagem("Leilão criado com sucesso.").build();
    }

    public LeilaoResponse criarLance(LanceRequest novoLance) {

        LeilaoItem item = leilaoItemRepo.findItemById(novoLance.getIdLeilao());
        List<LanceItem> listItem = item.getLances() != null ? item.getLances() : new ArrayList<LanceItem>();
        listItem.add(LanceItem.builder()
                .usuario(novoLance.getUsuario())
                .valorLance(novoLance.getValorLance())
                .dataLance(new Date()).build());

        item.setLances(listItem);

        LeilaoItem itemsUpdated = leilaoItemRepo.save(item);

        return LeilaoResponse.builder().isError(false).mensagem("Lance enviado com sucesso.").build();
    }

    public List<LeilaoItem> buscarLeiloes() {
        List<LeilaoItem> list = leilaoItemRepo.findAll();
        return list;
    }

    public LeilaoItem buscarLeilaoPeloId(String id) {
        LeilaoItem item = leilaoItemRepo.findItemById(id);
        return item;
    }

    public LeilaoItem atualizarLeilao(String id, LeilaoRequest novoLeilaoItem) {
        LeilaoItem item = leilaoItemRepo.findItemById(id);
        item.setNome(novoLeilaoItem.getNome());
        item.setValorInicial(novoLeilaoItem.getValorInicial());

        LeilaoItem itemAtualizado = leilaoItemRepo.save(item);
        return itemAtualizado;
    }

    public LeilaoResponse deletarLeilao(String id) {
        leilaoItemRepo.deleteById(id);
        return LeilaoResponse.builder().isError(false).mensagem("Lance criado com sucesso.").build();
    }
}
