package com.luizfrra.stockSim.Services.User;

import com.luizfrra.stockSim.EntitiesDomain.User.User;
import com.luizfrra.stockSim.Repositories.User.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) {

        if(userRepository.findByEmail(user.getEmail()).isEmpty()) {
            user.encodePassword(passwordEncoder);
            User userSaved = userRepository.save(user);
            userSaved.clearCredentials();
            return userSaved;
        }

        return null;
    }
}
