package org.esadev.spotifyapi.repository;

import org.esadev.spotifyapi.entity.SpotifyUnknownArtist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotifyUnknownArtistRepository extends JpaRepository<SpotifyUnknownArtist, String> {
}
