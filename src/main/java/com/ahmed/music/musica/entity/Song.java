package com.ahmed.music.musica.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @author Ghonim
 */
@Entity
@Table(name="Song",schema = "dbo")
@Where(clause = "active=true")
public class Song extends BaseEntity{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="song_name")
    private String songName;

    @ManyToOne
    @JoinColumn(name="singer_id", referencedColumnName = "id")
    private Singer singer;

    @ManyToOne
    @JoinColumn(name="album_id", referencedColumnName = "id")
    private Album album;

    public Song() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
