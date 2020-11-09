package com.ahmed.music.musica.rest;

import com.ahmed.music.musica.entity.Singer;
import com.ahmed.music.musica.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ghonim
 */
@RestController
@RequestMapping("/api/v1/singer")
public class SingerResource {

    @Autowired
    SingerService singerService;

    @GetMapping ("singers")
     public List<Singer> getAlbums (){
        return singerService.getSingers();
    }


}
