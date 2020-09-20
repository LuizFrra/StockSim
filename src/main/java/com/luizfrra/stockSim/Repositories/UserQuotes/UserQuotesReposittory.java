package com.luizfrra.stockSim.Repositories.UserQuotes;

import com.luizfrra.stockSim.EntitiesDomain.UserQuotes.UserQuotes;
import com.luizfrra.stockSim.EntitiesDomain.UserQuotes.UserQuotesKey;
import org.springframework.data.repository.CrudRepository;

public interface UserQuotesReposittory extends CrudRepository<UserQuotes, UserQuotesKey> {
}
