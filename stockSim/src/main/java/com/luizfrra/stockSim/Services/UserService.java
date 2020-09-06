package com.luizfrra.stockSim.Services;

import com.luizfrra.stockSim.EntitiesDomain.UserDomain;
import com.luizfrra.stockSim.Repositories.UserRepository;
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

    public UserDomain save(UserDomain user) {

        if(userRepository.findByEmail(user.getEmail()) == null) {
            user.encodePassword(passwordEncoder);
            UserDomain userSaved = userRepository.save(user);
            userSaved.clearCredentials();
            return userSaved;
        }

        return null;
    }
}
