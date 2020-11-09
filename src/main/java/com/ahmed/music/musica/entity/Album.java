package com.ahmed.music.musica.entity;


import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @author Ghonim
 */
@Entity
@Table(name="album",schema = "dbo")
@Where(clause = "active=true")
public class Album extends BaseEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="album_name")
    private String albumName;

    @ManyToOne
    @JoinColumn(name = "music_composer_id", referencedColumnName = "id")
    private MusicComposer musicComposer;

    @ManyToOne
    @JoinColumn(name="singer_id", referencedColumnName = "id")
    private Singer singer;

    public Album() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public MusicComposer getMusicComposer() {
        return musicComposer;
    }

    public void setMusicComposer(MusicComposer musicComposer) {
        this.musicComposer = musicComposer;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }
}
