package org.esadev.spotifyapi.controller;

import lombok.RequiredArgsConstructor;
import org.esadev.spotifyapi.dto.CountryArtistResponseDto;
import org.esadev.spotifyapi.service.CountryArtistService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artist")
public class ArtistController {
    private final CountryArtistService countryArtistService;

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @GetMapping("/followed-by-country")
    public CountryArtistResponseDto getFollowedArtistsGroupByCountry() {
        return countryArtistService.getFollowedArtistsByCountry();
    }
}
