package org.esadev.spotifyapi.repository;

import org.esadev.spotifyapi.entity.SpotifyArtist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpotifyArtistRepository extends JpaRepository<SpotifyArtist, String> {
    List<SpotifyArtist> findAllByIdIn(List<String> ids);
}
