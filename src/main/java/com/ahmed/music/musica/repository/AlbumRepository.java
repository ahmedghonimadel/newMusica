package com.ahmed.music.musica.repository;

import com.ahmed.music.musica.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ghonim
 */
@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query(value = "UPDATE  album SET album.active = false where album.id = :id", nativeQuery = true)
    @Modifying
    void delete(@Param("id") Long id);

    @Query(value = "select a from Album a INNER JOIN Singer s " +
            "on s.id=a.singer.id where s.firstName = :firstName and " +
            "s.lastName = :lastName")
    List<Album> findBySingerName(@Param("firstName") String firstName,
                                 @Param("lastName") String lastName);
}
