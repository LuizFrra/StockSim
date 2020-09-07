package com.luizfrra.stockSim.Services.Quote;

import com.luizfrra.stockSim.EntitiesDomain.Quote.Quote;
import com.luizfrra.stockSim.HGBrasil.HGAPIConsumer;
import com.luizfrra.stockSim.Repositories.Quote.QuoteRepository;
import com.luizfrra.stockSim.Utils.StringStockUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    private HGAPIConsumer hgapiConsumer;

    private  final Logger logger = LoggerFactory.getLogger(QuoteService.class);

    QuoteService(QuoteRepository quoteRepository, HGAPIConsumer hgapiConsumer) {
        this.quoteRepository = quoteRepository;
        this.hgapiConsumer = hgapiConsumer;
    }

    public Quote save(String symbol) {
        Optional<Quote> quoteDb = quoteRepository.findBySymbol(symbol);

        if(quoteDb.isEmpty() && !StringStockUtils.isNullOrEmptyOrOnlyWhiteSpace(symbol)) {
            Quote quote = hgapiConsumer.getQuote(symbol);
            Quote saved = quoteRepository.save(quote);
            return saved;
        }

        return null;
    }

    public Optional<Quote> findBySymbol(String symbol) {
        return quoteRepository.findBySymbol(symbol.toUpperCase());
    }

}
