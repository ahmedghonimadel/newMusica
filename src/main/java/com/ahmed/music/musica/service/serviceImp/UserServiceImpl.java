package com.ahmed.music.musica.service.serviceImp;

import com.ahmed.music.musica.entity.User;
import com.ahmed.music.musica.repository.UserRepository;
import com.ahmed.music.musica.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

/**
 * @author Ghonim
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User save(User user) {
        user.setActive(true);
        user.setCreatedDate(new Date());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteUser(userId);
    }

    public User updateUser(User user) {
        if (user.getPassword() != null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        user.setLastModifiedDate(new Date());
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }


    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

}