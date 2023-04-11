package org.esadev.spotifyapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class CountryArtistResponseDto {
    List<CountryArtistDto> countryArtists;
}
