package br.com.leilao.api.service;

import br.com.leilao.api.item.LanceItem;
import br.com.leilao.api.item.LeilaoItem;
import br.com.leilao.api.repository.ItemRepository;
import br.com.leilao.api.request.LanceRequest;
import br.com.leilao.api.request.LeilaoRequest;
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

    public void criarLeilao(LeilaoRequest novoLeilao) {

        String easy = RandomArgsUtils.digits + "ACEFGHJKLMNPQRUVWXYabcdefhijkprstuvwx";
        RandomArgsUtils randomDigits = new RandomArgsUtils(23, new SecureRandom(), easy);

        leilaoItemRepo.save(LeilaoItem.builder()
                .id(randomDigits.nextString())
                .nome(novoLeilao.getNome())
                .usuario(novoLeilao.getUsuario())
                .valorInicial(novoLeilao.getValorInicial())
                .dataAbertura(new Date())
                .build());
    }

    public void criarLance(LanceRequest novoLance) {

        LeilaoItem item = leilaoItemRepo.findItemById(novoLance.getIdLeilao());
        List<LanceItem> listItem = item.getLances() != null ? item.getLances() : new ArrayList<LanceItem>();
        listItem.add(LanceItem.builder()
                .usuario(novoLance.getUsuario())
                .valorLance(novoLance.getValorLance())
                .dataLance(new Date()).build());

        item.setLances(listItem);

        LeilaoItem itemsUpdated = leilaoItemRepo.save(item);
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

    public void deletarLeilao(String id) {
        leilaoItemRepo.deleteById(id);
    }
}
