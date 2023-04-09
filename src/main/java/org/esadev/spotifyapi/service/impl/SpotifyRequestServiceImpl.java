package org.esadev.spotifyapi.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.hc.core5.http.ParseException;
import org.esadev.spotifyapi.exception.SpotifyRequestException;
import org.esadev.spotifyapi.filter.SpotifyCodeFilter;
import org.esadev.spotifyapi.service.SpotifyRequestService;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.specification.PagingCursorbased;
import se.michaelthelin.spotify.requests.data.follow.GetUsersFollowedArtistsRequest;

import java.io.IOException;

import static java.util.Optional.ofNullable;

@Service
public class SpotifyRequestServiceImpl implements SpotifyRequestService {

    public static final int DEFAULT_ARTIST_LIMIT = 20;
    public static final String RETRIEVING_FOLLOWED_ARTISTS_EXCEPTION_MESSAGE = "Error retrieving followed artists";

    @Override
    public <T extends AbstractModelObject> PagingCursorbased<T> getUsersFollowedArtistsRequest(Integer limit, String after, ModelObjectType type) {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(SpotifyCodeFilter.getCookieValue())
                .build();
        GetUsersFollowedArtistsRequest.Builder getUsersFollowedArtistsRequest =
                spotifyApi.getUsersFollowedArtists(type)
                        .limit(ofNullable(limit).orElse(DEFAULT_ARTIST_LIMIT));
        if (StringUtils.isNotEmpty(after)) {
            getUsersFollowedArtistsRequest.after(after);
        }

        try {
            return (PagingCursorbased<T>) getUsersFollowedArtistsRequest.build().execute();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw new SpotifyRequestException(RETRIEVING_FOLLOWED_ARTISTS_EXCEPTION_MESSAGE, e);
        }
    }
}
