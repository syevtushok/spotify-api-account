package org.esadev.spotifyapi.service;

import org.esadev.spotifyapi.entity.SpotifyUnknownArtist;

import java.util.List;

public interface SpotifyUnknownService {
    void saveUnknownSpotifyArtists(List<SpotifyUnknownArtist> unknownArtists);
}
