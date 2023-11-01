package org.esadev.spotifyapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.esadev.spotifyapi.dto.FollowedArtistsResponse;
import org.esadev.spotifyapi.entity.SpotifyArtist;
import org.esadev.spotifyapi.entity.SpotifyUnknownArtist;
import org.esadev.spotifyapi.repository.SpotifyArtistRepository;
import org.esadev.spotifyapi.service.SpotifyRequestService;
import org.esadev.spotifyapi.service.SpotifyUnknownService;
import org.esadev.spotifyapi.service.UserSpotifyService;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.PagingCursorbased;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static se.michaelthelin.spotify.enums.ModelObjectType.ARTIST;

@Service
@RequiredArgsConstructor
public class UserSpotifyServiceImpl implements UserSpotifyService {
    private final SpotifyArtistRepository spotifyArtistRepository;
    private final SpotifyRequestService spotifyRequestService;
    private final SpotifyUnknownService spotifyUnknownService;
    @Override
    public FollowedArtistsResponse getFollowedArtists(String after, Integer limit) {
        PagingCursorbased<Artist> pagingCursorbased = spotifyRequestService.getUsersFollowedArtistsRequest(limit, after, ARTIST);
        Artist[] items = pagingCursorbased.getItems();
        List<String> list = Arrays.stream(items).map(Artist::getId).toList();

        List<SpotifyArtist> allByIdIn = spotifyArtistRepository.findAllByIdIn(list);
        List<String> factIds = allByIdIn.stream().map(SpotifyArtist::getId).toList();
        List<SpotifyUnknownArtist> unknownArtists = new ArrayList<>();
        Arrays.stream(items).filter(artist -> !factIds.contains(artist.getId())).forEach(artist -> {
            addToExternalList(artist, allByIdIn);
            addToPostProcess(artist, unknownArtists);
        });
        if (unknownArtists.isEmpty()) {
            spotifyUnknownService.saveUnknownSpotifyArtists(unknownArtists);
        }

        FollowedArtistsResponse followedArtistsResponse = new FollowedArtistsResponse();
        followedArtistsResponse.setFollowedArtists(allByIdIn);
        followedArtistsResponse.setAfter(pagingCursorbased.getCursors()[0].getAfter());
        followedArtistsResponse.setTotal(pagingCursorbased.getTotal());
        return followedArtistsResponse;
    }

    private void addToPostProcess(Artist artist, List<SpotifyUnknownArtist> unknownArtists) {
        SpotifyUnknownArtist spotifyUnknownArtist = new SpotifyUnknownArtist();
        spotifyUnknownArtist.setCountry("UNKNOWN");
        spotifyUnknownArtist.setId(artist.getId());
        spotifyUnknownArtist.setName(artist.getName());
        unknownArtists.add(spotifyUnknownArtist);
    }

    private void addToExternalList(Artist artist, List<SpotifyArtist> allByIdIn) {
        SpotifyArtist spotifyArtist = new SpotifyArtist();
        spotifyArtist.setCountry("UNKNOWN");
        spotifyArtist.setId(artist.getId());
        spotifyArtist.setName(artist.getName());
        allByIdIn.add(spotifyArtist);
    }
}
