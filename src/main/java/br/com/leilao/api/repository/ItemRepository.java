package br.com.leilao.api.repository;

import br.com.leilao.api.item.LeilaoItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<LeilaoItem, String> {

    @Query("{id:'?0'}")
    LeilaoItem findItemById(String id);

    @Query(value="{usuario:'?0'}", fields="{'nome' : 1, 'dataAbertura' : 1, 'valorInicial' : 1, 'lances' : 1}")
    List<LeilaoItem> findAllLeilaoUserById(String id);

    public long count();

}
