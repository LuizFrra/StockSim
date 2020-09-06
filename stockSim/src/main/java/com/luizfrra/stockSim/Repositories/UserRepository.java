package com.luizfrra.stockSim.Repositories;

import com.luizfrra.stockSim.EntitiesDomain.UserDomain;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDomain, Long> {

    UserDomain findByEmail(String email);

}
