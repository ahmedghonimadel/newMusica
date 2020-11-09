package com.ahmed.music.musica.service;

import com.ahmed.music.musica.dto.AlbumDto;
import com.ahmed.music.musica.entity.Album;

import java.util.List;
import java.util.Optional;

/**
 * @author Ghonim
 */
public interface AlbumService  {
    List<Album> getAlbums();
    void deleteAlbum(long id);
    void saveAlbum(AlbumDto albumDto);
    List<Album> findBySingerName(String firstName, String lastName);
    Optional<Album> getAlbumById(long id);
    Album update(long id,Album album);
}
