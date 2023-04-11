package org.esadev.spotifyapi.dto;

import lombok.Builder;
import lombok.Data;
import org.esadev.spotifyapi.entity.SpotifyArtist;

import java.util.List;

@Data
@Builder
public class CountryArtistDto {
    private String country;
    private List<SpotifyArtist> spotifyArtists;
    private Integer count;
}
