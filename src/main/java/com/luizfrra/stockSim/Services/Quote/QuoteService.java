package com.luizfrra.stockSim.Services.Quote;

import com.luizfrra.stockSim.EntitiesDomain.Quote.Quote;
import com.luizfrra.stockSim.HGBrasil.HGAPIConsumer;
import com.luizfrra.stockSim.Repositories.Quote.QuoteRepository;
import com.luizfrra.stockSim.Services.Commons.IBaseService;
import com.luizfrra.stockSim.Utils.DateStockUtils;
import com.luizfrra.stockSim.Utils.StringStockUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuoteService implements IBaseService<Quote, String> {

    private final QuoteRepository quoteRepository;

    private HGAPIConsumer hgapiConsumer;

    private  final Logger logger = LoggerFactory.getLogger(QuoteService.class);

    QuoteService(QuoteRepository quoteRepository, HGAPIConsumer hgapiConsumer) {
        this.quoteRepository = quoteRepository;
        this.hgapiConsumer = hgapiConsumer;
    }

    @Override
    public Quote save(Quote quote) throws ConstraintViolationException {
        Optional<Quote> quoteDb = quoteRepository.findBySymbol(quote.getSymbol());

        if(quoteDb.isEmpty() && !StringStockUtils.isNullOrEmptyOrOnlyWhiteSpace(quote.getSymbol())) {
            Quote quoteResult = hgapiConsumer.getQuote(quote.getSymbol());
            try {
                Quote saved = quoteRepository.save(quoteResult);
                return saved;
            } catch (ConstraintViolationException ex) {
                throw new ConstraintViolationException("Data Already Exist", ex.getSQLException(), ex.getConstraintName());
            }
        }

        return null;
    }

    @Override
    public Optional<Quote> findById(String symbol) {
        return findAndUpdateQuoteBySymbol(symbol.toUpperCase());
    }

    private Optional<Quote> findAndUpdateQuoteBySymbol(String symbol) {
        Optional<Quote> quoteOptional = quoteRepository.findBySymbol(symbol);

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

    @Override
    public List<Quote> findAll() {
        List<Quote> listOfQuote = new ArrayList<>();
        Iterable<Quote> iteradorQuotes = quoteRepository.findAll();
        iteradorQuotes.forEach(listOfQuote::add);
        return listOfQuote;
    }
}
