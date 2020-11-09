package com.ahmed.music.musica.service.serviceImp;

import com.ahmed.music.musica.dto.AlbumDto;
import com.ahmed.music.musica.entity.Album;
import com.ahmed.music.musica.repository.AlbumRepository;
import com.ahmed.music.musica.repository.MusicComposerRepository;
import com.ahmed.music.musica.repository.SingerRepository;
import com.ahmed.music.musica.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Ghonim
 */
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    SingerRepository singerRepository;
    @Autowired
    MusicComposerRepository musicComposerRepository;

    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }

    public Optional<Album> getAlbumById(long id) {
        return albumRepository.findById(id);
    }

    public void deleteAlbum(long id) {
        albumRepository.delete(id);
    }

    public void saveAlbum(AlbumDto albumDto) {
        Album album = new Album();
        if (albumDto != null) {
            album.setAlbumName(albumDto.getName());
            if (albumDto.getMusicComposer() != null) {
                album.setMusicComposer(albumDto.getMusicComposer());
            }
            if (albumDto.getSinger() != null) {
                album.setSinger(albumDto.getSinger());
            }
            album.setCreatedDate(new Date());
            album.setActive(true);
            albumRepository.save(album);
        }
        if(albumDto.getSinger()!=null){
            album.getSinger().setCreatedDate(new Date());
            album.getSinger().setActive(true);
            singerRepository.save(albumDto.getSinger());
        }
        if(albumDto.getMusicComposer()!=null){
            album.getMusicComposer().setActive(true);
            album.getMusicComposer().setCreatedDate(new Date());
        musicComposerRepository.save(albumDto.getMusicComposer());}
    }

    public Album update(long id,Album album) {
        if (albumRepository.findById(id).isPresent()){
            Album newAlbum=albumRepository.findById(id).get();
            newAlbum.setAlbumName(album.getAlbumName());
            newAlbum.setCreatedDate(new Date());
            return albumRepository.save(newAlbum);
            }return null;
    }

    public List<Album> findBySingerName(String firstName, String lastName) {
        return albumRepository.findBySingerName(firstName, lastName);
    }
}
