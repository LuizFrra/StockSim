package com.luizfrra.stockSim.Repositories.Quote;

import com.luizfrra.stockSim.EntitiesDomain.Quote.Quote;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface QuoteRepository extends CrudRepository<Quote, String> {

    Optional<Quote> findBySymbol(String symbol);

}
