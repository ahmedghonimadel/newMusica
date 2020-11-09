package com.ahmed.music.musica.service;

import com.ahmed.music.musica.entity.Song;

import java.util.List;
import java.util.Optional;

/**
 * @author Ghonim
 */
public interface SongService {
    List<Song> findAll();
    Song findBySongName(String songName);
    List<Song> getBySingerName(String firstNmae,String lastName);
    Optional<Song> findById(long id);
}
