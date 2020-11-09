package com.ahmed.music.musica.service.serviceImp;


import com.ahmed.music.musica.entity.Song;
import com.ahmed.music.musica.repository.SongRepository;
import com.ahmed.music.musica.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Ghonim
 */
@Service
@Transactional
public class SongServiceImpl implements SongService {
    @Autowired
    SongRepository songRepository;

   public  List<Song> findAll(){
        return songRepository.findAll() ;
    }

    public Song findBySongName(String songName){
        return songRepository.findBySongName(songName);
    }
    public Optional<Song> findById(long id){
       return songRepository.findById(id);
    }
    public List<Song> getBySingerName(String firstNmae,String lastName){
        return songRepository.findBySingerName(firstNmae,lastName);
    }
}
