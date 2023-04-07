package org.esadev.spotifyapi.impl;

import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.esadev.spotifyapi.SpotifyArtistService;
import org.esadev.spotifyapi.entity.SpotifyArtist;
import org.esadev.spotifyapi.filter.SpotifyCodeFilter;
import org.esadev.spotifyapi.repository.SpotifyArtistRepository;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.PagingCursorbased;
import se.michaelthelin.spotify.requests.data.follow.GetUsersFollowedArtistsRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static se.michaelthelin.spotify.enums.ModelObjectType.ARTIST;

@Service
@RequiredArgsConstructor
public class SpotifyArtistServiceImpl implements SpotifyArtistService {
    private final SpotifyArtistRepository spotifyArtistRepository;
    String cookieValue = SpotifyCodeFilter.getCookieValue();

    @Override
    public List<SpotifyArtist> getFollowedArtists() {

        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(SpotifyCodeFilter.getCookieValue())
                .build();
        GetUsersFollowedArtistsRequest getUsersFollowedArtistsRequest =
                spotifyApi.getUsersFollowedArtists(ARTIST)
                        .limit(50)
                        .build();

        try {
            PagingCursorbased<Artist> pagingCursorbased = getUsersFollowedArtistsRequest.execute();
            Artist[] items = pagingCursorbased.getItems();
            List<String> list = Arrays.stream(items).map(Artist::getId).toList();

            List<SpotifyArtist> allByIdIn = spotifyArtistRepository.findAllByIdIn(list);
            List<String> factIds = allByIdIn.stream().map(SpotifyArtist::getId).toList();
            Arrays.stream(items).filter(artist -> !factIds.contains(artist.getId())).forEach(artist -> {
                SpotifyArtist spotifyArtist = new SpotifyArtist();
                spotifyArtist.setCountry("UNKNOWN");
                spotifyArtist.setId(artist.getId());
                spotifyArtist.setName(artist.getName());
                allByIdIn.add(spotifyArtist);
            });
            return allByIdIn;
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw new RuntimeException("Error retrieving followed artists", e);
        }
    }
}
