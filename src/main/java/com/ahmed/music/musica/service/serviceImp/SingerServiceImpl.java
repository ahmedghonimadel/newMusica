package com.ahmed.music.musica.service.serviceImp;


import com.ahmed.music.musica.entity.Singer;
import com.ahmed.music.musica.repository.SingerRepository;
import com.ahmed.music.musica.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ghonim
 */
@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    SingerRepository singerRepository;

    public List<Singer> getSingers(){
        return singerRepository.findAll();
    }
}
