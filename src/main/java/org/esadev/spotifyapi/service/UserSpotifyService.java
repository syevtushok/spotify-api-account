package org.esadev.spotifyapi.service;

import org.esadev.spotifyapi.dto.FollowedArtistsResponse;

public interface UserSpotifyService {

    FollowedArtistsResponse getFollowedArtists(String after, Integer limit);
}
