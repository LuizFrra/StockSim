package com.luizfrra.stockSim.Services.Quote;

import com.luizfrra.stockSim.EntitiesDomain.Quote.Quote;
import com.luizfrra.stockSim.HGBrasil.HGAPIConsumer;
import com.luizfrra.stockSim.Repositories.Quote.QuoteRepository;
import com.luizfrra.stockSim.Utils.DateStockUtils;
import com.luizfrra.stockSim.Utils.StringStockUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
        Optional<Quote> quoteDb = findBySymbol(symbol);

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

    public Optional<Quote> findAndUpdateQuoteBySymbol(String symbol) {
        Optional<Quote> quoteOptional = findBySymbol(symbol);

        if(quoteOptional.isEmpty())
            return Optional.empty();

        Quote quote = quoteOptional.get();

        Instant utcNow = Instant.now();
        Instant quoteUpdatedAt = quote.getUpdatedAt().toInstant();
        long secondsPassed = utcNow.getEpochSecond() - quoteUpdatedAt.getEpochSecond();

        if(secondsPassed <= 3600 || !DateStockUtils.isBusinessHour(utcNow) || !DateStockUtils.isWeekDay(utcNow))
            return quoteOptional;

        quote = hgapiConsumer.getQuote(symbol);
        Quote quoteUpdated = quoteRepository.save(quote);

        return Optional.of(quoteUpdated);
    }
}
