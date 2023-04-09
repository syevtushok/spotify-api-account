package org.esadev.spotifyapi.dto;

import lombok.Data;
import org.esadev.spotifyapi.entity.SpotifyArtist;

import java.util.List;

@Data
public class FollowedArtistsResponse {
    List<SpotifyArtist> followedArtists;
    Integer total;
    String after;
}
