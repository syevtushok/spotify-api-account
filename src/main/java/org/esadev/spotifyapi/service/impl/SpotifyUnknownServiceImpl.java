package org.esadev.spotifyapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.esadev.spotifyapi.entity.SpotifyUnknownArtist;
import org.esadev.spotifyapi.repository.SpotifyUnknownArtistRepository;
import org.esadev.spotifyapi.service.SpotifyUnknownService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotifyUnknownServiceImpl implements SpotifyUnknownService {
    private final SpotifyUnknownArtistRepository spotifyUnknownArtistRepository;

    @Override
    public void saveUnknownSpotifyArtists(List<SpotifyUnknownArtist> unknownArtists) {
        spotifyUnknownArtistRepository.saveAll(unknownArtists);
    }
}
