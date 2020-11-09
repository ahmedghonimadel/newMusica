package com.ahmed.music.musica.repository;

import com.ahmed.music.musica.entity.MusicComposer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicComposerRepository extends JpaRepository<MusicComposer, Long> {
}
