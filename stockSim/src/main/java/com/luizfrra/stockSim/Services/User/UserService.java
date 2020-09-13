package com.luizfrra.stockSim.Services.User;

import com.luizfrra.stockSim.EntitiesDomain.User.User;
import com.luizfrra.stockSim.EntitiesDomain.UserQuotes.UserQuotes;
import com.luizfrra.stockSim.Repositories.User.UserRepository;
import com.luizfrra.stockSim.Services.Commons.IBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IBaseService<User, String> {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(Long.parseLong(id));
    }

    @Override
    public User save(User user) {

        if(userRepository.findByEmail(user.getEmail()).isEmpty()) {
            user.encodePassword(passwordEncoder);
            User userSaved = userRepository.save(user);
            userSaved.clearCredentials();
            return userSaved;
        }

        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> listOfUser = new ArrayList<>();
        Iterable<User> iteradorUser = userRepository.findAll();
        iteradorUser.forEach(listOfUser::add);
        return listOfUser;
    }
}
