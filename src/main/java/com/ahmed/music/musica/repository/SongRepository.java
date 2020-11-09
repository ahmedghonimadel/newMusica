package com.ahmed.music.musica.repository;

import com.ahmed.music.musica.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ghonim
 */
@Repository
public interface SongRepository extends JpaRepository<Song,Long> {

     Song findBySongName(String songName);

      @Query(value = "select s from Song s INNER JOIN Singer n " +
              "on n.id=s.singer.id where n.firstName = :firstName and n.lastName= :lastName " )
      List<Song> findBySingerName(@Param("firstName") String firstName,@Param("lastName")String lastName);
    }
