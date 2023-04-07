package org.esadev.spotifyapi;

import org.esadev.spotifyapi.entity.SpotifyArtist;

import java.util.List;

public interface SpotifyArtistService {

    List<SpotifyArtist> getFollowedArtists();
}
