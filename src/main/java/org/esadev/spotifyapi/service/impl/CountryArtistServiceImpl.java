package org.esadev.spotifyapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.esadev.spotifyapi.dto.CountryArtistDto;
import org.esadev.spotifyapi.dto.CountryArtistResponseDto;
import org.esadev.spotifyapi.dto.FollowedArtistsResponse;
import org.esadev.spotifyapi.entity.SpotifyArtist;
import org.esadev.spotifyapi.service.CountryArtistService;
import org.esadev.spotifyapi.service.UserSpotifyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryArtistServiceImpl implements CountryArtistService {
    public static final int DEFAULT_LIMIT_ARTIST = 50;
    private final UserSpotifyService spotifyService;

    @Override
    public CountryArtistResponseDto getFollowedArtistsByCountry() {
        int processed = 0;

        FollowedArtistsResponse followedArtists = spotifyService.getFollowedArtists(StringUtils.EMPTY, DEFAULT_LIMIT_ARTIST);
        Set<SpotifyArtist> spotifyArtists = new HashSet<>(followedArtists.getFollowedArtists());

        do {
            spotifyArtists.addAll(followedArtists.getFollowedArtists());
            followedArtists = spotifyService.getFollowedArtists(followedArtists.getAfter(), DEFAULT_LIMIT_ARTIST);
            processed += DEFAULT_LIMIT_ARTIST;
        } while (processed < followedArtists.getTotal());

        CountryArtistResponseDto countryArtistResponseDto = new CountryArtistResponseDto();
        countryArtistResponseDto.setCountryArtists(getCountryArtistDtos(spotifyArtists));

        return countryArtistResponseDto;
    }

    private List<CountryArtistDto> getCountryArtistDtos(Set<SpotifyArtist> spotifyArtists) {
        Map<String, List<SpotifyArtist>> artistsByCountry = spotifyArtists.stream()
                .collect(Collectors.groupingBy(SpotifyArtist::getCountry));

        List<CountryArtistDto> countryArtistDtoList = new ArrayList<>();
        artistsByCountry.forEach((country, artists) -> {
            CountryArtistDto countryArtistDto = CountryArtistDto.builder()
                    .spotifyArtists(artists)
                    .count(artists.size())
                    .country(country)
                    .build();
            countryArtistDtoList.add(countryArtistDto);
        });
        return countryArtistDtoList;
    }
}
