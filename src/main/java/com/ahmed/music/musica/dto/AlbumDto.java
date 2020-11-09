package com.ahmed.music.musica.dto;

import com.ahmed.music.musica.entity.MusicComposer;
import com.ahmed.music.musica.entity.Singer;

public class AlbumDto {
    private String name;
    private Singer singer;
    private MusicComposer musicComposer;

       public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public MusicComposer getMusicComposer() {
        return musicComposer;
    }

    public void setMusicComposer(MusicComposer musicComposer) {
        this.musicComposer = musicComposer;
    }
}
