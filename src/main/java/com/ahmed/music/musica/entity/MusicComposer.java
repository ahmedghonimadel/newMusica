package com.ahmed.music.musica.entity;

import javax.persistence.*;

/**
 * @author Ghonim
 */
@Entity
@Table(name="music_composer",schema = "dbo")
public class MusicComposer extends BaseEntity{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="music_composer_name")
    private String musicComposerName;

    public MusicComposer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMusicComposerName() {
        return musicComposerName;
    }

    public void setMusicComposerName(String musicComposerName) {
        this.musicComposerName = musicComposerName;
    }
}
