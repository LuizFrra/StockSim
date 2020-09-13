package com.luizfrra.stockSim.Services.UserQuotes;

import com.luizfrra.stockSim.EntitiesDomain.UserQuotes.UserQuotes;
import com.luizfrra.stockSim.Repositories.UserQuotes.UserQuotesReposittory;
import com.luizfrra.stockSim.Services.Commons.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserQuotesService implements IBaseService<UserQuotes, String> {

    @Autowired
    public UserQuotesReposittory userQuotesReposittory;

    @Override
    public Optional<UserQuotes> findById(String id) {
        return Optional.empty();
    }

    @Override
    public UserQuotes save(UserQuotes data) {
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
