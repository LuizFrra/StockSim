package com.luizfrra.stockSim.Repositories.User;

import com.luizfrra.stockSim.EntitiesDomain.User.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByEmail(String email);

}
