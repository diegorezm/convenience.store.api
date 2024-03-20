package com.conveniencestore.conveniencestore.components;

import com.conveniencestore.conveniencestore.domain.users.User;
import com.conveniencestore.conveniencestore.domain.users.UserDTO;
import com.conveniencestore.conveniencestore.domain.users.UserRoles;
import com.conveniencestore.conveniencestore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DummyAdminData implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        String password = new BCryptPasswordEncoder().encode("123456");
        user.setEmail("diego@email.com");
        user.setUsername("diego");
        user.setRole(UserRoles.ADMIN);
        user.setPassword(password);
        this.userRepository.save(user);
    }
}
