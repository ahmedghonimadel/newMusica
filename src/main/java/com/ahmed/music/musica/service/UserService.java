package com.ahmed.music.musica.service;

import com.ahmed.music.musica.entity.User;

import java.util.Optional;

/**
 * @author Ghonim
 */
public interface UserService {
    Optional<User> findById(Long userId);
    User save(User user);
    void deleteUser(Long userId);
    User updateUser(User user);
    User findByEmail(String email);
}
