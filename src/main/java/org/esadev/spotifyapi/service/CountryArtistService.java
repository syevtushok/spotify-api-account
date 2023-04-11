package org.esadev.spotifyapi.service;

import org.esadev.spotifyapi.dto.CountryArtistResponseDto;

public interface CountryArtistService {
    CountryArtistResponseDto getFollowedArtistsByCountry();
}
