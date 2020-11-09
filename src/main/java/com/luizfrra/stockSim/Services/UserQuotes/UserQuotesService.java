package com.luizfrra.stockSim.Services.UserQuotes;

import com.luizfrra.stockSim.EntitiesDomain.Quote.Quote;
import com.luizfrra.stockSim.EntitiesDomain.User.User;
import com.luizfrra.stockSim.EntitiesDomain.UserQuotes.UserQuotes;
import com.luizfrra.stockSim.Exceptions.NotMeetRequisitesException;
import com.luizfrra.stockSim.Exceptions.QuoteNotExistException;
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
        Optional<Quote> quoteOp = quoteService.findById(symbol);
        User buyer = userService.findById(data.getId().getUserId().toString()).get();

        if(quoteOp.isEmpty())
            throw new QuoteNotExistException("The Quote That You Would Like To Buy Not Exist", data);

        Quote quote = quoteOp.get();

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
        } else {
            throw new NotMeetRequisitesException("You Haven't Enought Money To That Buy Quote", data);
        }

    }

    @Override
    public List<UserQuotes> findAll() {
        List<UserQuotes> listOfUserQuotes = new ArrayList<>();
        Iterable<UserQuotes> iteradorUserQuotes = userQuotesReposittory.findAll();
        iteradorUserQuotes.forEach(listOfUserQuotes::add);
        return listOfUserQuotes;
    }

    public UserQuotes sellQuote(UserQuotes data) {

        String symbol = data.getId().getQuotedId();
        Optional<Quote> quoteOp = quoteService.findById(symbol);

        User seller = userService.findById(data.getId().getUserId()).get();

        if(quoteOp.isEmpty())
            throw new QuoteNotExistException("The Quote that you would like to sell not exist", data);

        Quote quote = quoteOp.get();

        UserQuotes currentDataQuote = null;

        List<UserQuotes> userDataQuotes = seller.getQuotes().stream().filter(p -> p.getId().getQuotedId().equals(symbol))
                .collect(Collectors.toList());

        if(userDataQuotes.size() == 1) currentDataQuote = userDataQuotes.get(0);

        int currentNumberOfQuotes = currentDataQuote != null ? currentDataQuote.getNumberOfQuotes() : 0;
        double currentAveragePrice = currentDataQuote != null ? currentDataQuote.getAveragePrice() : 0;


        int futureNumberOfQuotes = currentNumberOfQuotes - data.getNumberOfQuotes();

        if(futureNumberOfQuotes >= 0) {
            seller.creditQuote(quote.getPrice(), data.getNumberOfQuotes());
            UserQuotes dataToSave = new UserQuotes(data.getId(), seller, quote, currentAveragePrice, futureNumberOfQuotes);
            dataToSave = userQuotesReposittory.save(dataToSave);

            if(futureNumberOfQuotes == 0)
                userQuotesReposittory.delete(dataToSave);

            return dataToSave;
        } else {
            throw new NotMeetRequisitesException("You Haven't Enough Quotes to Sell", data);
        }
    }
}
