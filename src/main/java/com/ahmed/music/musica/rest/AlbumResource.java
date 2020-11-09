package com.ahmed.music.musica.rest;


import com.ahmed.music.musica.dto.AlbumDto;
import com.ahmed.music.musica.entity.Album;
import com.ahmed.music.musica.entity.User;
import com.ahmed.music.musica.exception.ResourceNotFoundException;
import com.ahmed.music.musica.response.SuccessResponse;
import com.ahmed.music.musica.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ghonim
 */
@RestController
@RequestMapping("/api/v1/album")
public class AlbumResource {

    @Autowired
    AlbumService albumService;

    @RequestMapping(value = "/albums", method = RequestMethod.GET)
    public List<Album> getAlbums() {
        return albumService.getAlbums();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SuccessResponse<Album> getById(@PathVariable(name = "id") long id) {
        Album album = albumService.getAlbumById(id).orElseThrow(() -> new ResourceNotFoundException(User.class, String.valueOf(id)));
        return new SuccessResponse<>(album);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    public void deleteById(@PathVariable(name = "id") long id) {
        Album album = albumService.getAlbumById(id).orElseThrow(() -> new ResourceNotFoundException(User.class, String.valueOf(id)));
        albumService.deleteAlbum(id);
    }

    @PutMapping(value = "update/{id}")
    public SuccessResponse<Album> updateById(@PathVariable("id") long id,@RequestBody Album album){
       albumService.getAlbumById(id).orElseThrow(() -> new ResourceNotFoundException(User.class, String.valueOf(id)));
       return new SuccessResponse<>(albumService.update(id,album));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void save(@RequestBody AlbumDto albumDto) {
        albumService.saveAlbum(albumDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get-by-singer/{firstName}/{lastName}")
    public SuccessResponse<List<Album>> getBySinger(@PathVariable("firstName") String firstName,@PathVariable("lastName") String lastName) {
        return new SuccessResponse<>(albumService.findBySingerName(firstName, lastName));
    }

}
