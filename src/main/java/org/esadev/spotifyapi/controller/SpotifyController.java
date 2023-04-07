package org.esadev.spotifyapi.controller;

import lombok.RequiredArgsConstructor;
import org.esadev.spotifyapi.SpotifyArtistService;
import org.esadev.spotifyapi.entity.SpotifyArtist;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SpotifyController {
    private final SpotifyArtistService spotifyArtistService;

    @GetMapping("/followed-artists")
    public List<SpotifyArtist> getFollowedArtists() {
        return spotifyArtistService.getFollowedArtists();
    }
}
