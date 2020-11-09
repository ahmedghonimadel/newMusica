package com.ahmed.music.musica.repository;

import com.ahmed.music.musica.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Ghonim
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query("update User u set u.active = 0 where u.id = :id")
    void deleteUser(@Param("id") Long id);

    User findByUsername(String username);

    User findByEmail(String email);


}