package com.ahmed.music.musica.repository;

import com.ahmed.music.musica.entity.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ghonim
 */
@Repository
public interface SingerRepository extends JpaRepository<Singer,Long> {

}
