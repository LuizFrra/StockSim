package com.luizfrra.stockSim.Services.UserQuotes;

import com.luizfrra.stockSim.EntitiesDomain.Quote.Quote;
import com.luizfrra.stockSim.EntitiesDomain.User.User;
import com.luizfrra.stockSim.EntitiesDomain.UserQuotes.UserQuotes;
import com.luizfrra.stockSim.Repositories.UserQuotes.UserQuotesReposittory;
import com.luizfrra.stockSim.Services.Commons.IBaseService;
import com.luizfrra.stockSim.Services.Quote.QuoteService;
import com.luizfrra.stockSim.Services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserQuotesService implements IBaseService<UserQuotes, String> {

    @Autowired
    public UserQuotesReposittory userQuotesReposittory;

    @Autowired
    public QuoteService quoteService;

    @Autowired
    public UserService userService;

    @Override
    public Optional<UserQuotes> findById(String id) {
        return Optional.empty();
    }

    @Override
    public UserQuotes save(UserQuotes data) {
        String symbol = data.getId().getQuotedId();
        Quote quote = (Quote) quoteService.findById(symbol).get();
        User buyer = userService.findById(data.getId().getUserId().toString()).get();

        UserQuotes currentDataQuote = null;

        List<UserQuotes> userDataQuotes = buyer.getQuotes().stream().filter(p -> p.getId().getQuotedId().equals(symbol))
                .collect(Collectors.toList());

        if(userDataQuotes.size() == 1) currentDataQuote = userDataQuotes.get(0);

        int currentNumberOfQuotes = currentDataQuote != null ? currentDataQuote.getNumberOfQuotes() : 0;
        double currentAveragePrice = currentDataQuote != null ? currentDataQuote.getAveragePrice() : 0;

        double totalAveragePrice = ((quote.getPrice() * data.getNumberOfQuotes()) + (currentAveragePrice * currentNumberOfQuotes))
                /(data.getNumberOfQuotes() + currentNumberOfQuotes);

        int totalNumberOfQuotes = data.getNumberOfQuotes() + currentNumberOfQuotes;

        if(buyer.debitQuote(quote.getPrice(), data.getNumberOfQuotes()) >= 0) {
            UserQuotes dataToSave = new UserQuotes(data.getId(), buyer, quote, totalAveragePrice, totalNumberOfQuotes);
            return userQuotesReposittory.save(dataToSave);
        }

        return null;
    }

    @Override
    public List<UserQuotes> findAll() {
        List<UserQuotes> listOfUserQuotes = new ArrayList<>();
        Iterable<UserQuotes> iteradorUserQuotes = userQuotesReposittory.findAll();
        iteradorUserQuotes.forEach(listOfUserQuotes::add);
        return listOfUserQuotes;
    }
}