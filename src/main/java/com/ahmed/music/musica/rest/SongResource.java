package com.ahmed.music.musica.rest;

import com.ahmed.music.musica.entity.Song;
import com.ahmed.music.musica.entity.User;
import com.ahmed.music.musica.exception.ResourceNotFoundException;
import com.ahmed.music.musica.response.SuccessResponse;
import com.ahmed.music.musica.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ghonim
 */
@RestController
@RequestMapping("/api/v1/song")
public class SongResource {

    @Autowired
    SongService songService;

    @GetMapping(value = "/songs")
    public SuccessResponse<List<Song>> getSongs(){
      return new SuccessResponse<>(songService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SuccessResponse<Song> getById(@PathVariable(name = "id") long id) {
        Song song = songService.findById(id).orElseThrow(() -> new ResourceNotFoundException(User.class, String.valueOf(id)));
        return new SuccessResponse<>(song);
    }

    @GetMapping (value ="/name/{name}")
    public SuccessResponse<Song> getByName(@PathVariable("name") String name){
        return new SuccessResponse<>(songService.findBySongName(name));
    }

    @GetMapping(value = "/singer/{firstName}/{lastName}")
    public SuccessResponse<List<Song>> getBySinger(@PathVariable("firstName") String firstName,@PathVariable("lastName") String lastName){
       return  new SuccessResponse<>(songService.getBySingerName(firstName,lastName));
    }

}
