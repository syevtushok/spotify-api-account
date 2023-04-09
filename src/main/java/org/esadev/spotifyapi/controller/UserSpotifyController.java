package org.esadev.spotifyapi.controller;

import lombok.RequiredArgsConstructor;
import org.esadev.spotifyapi.dto.FollowedArtistsResponse;
import org.esadev.spotifyapi.service.UserSpotifyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserSpotifyController {
    private final UserSpotifyService userSpotifyService;

    @GetMapping("/followed-artists")
    public FollowedArtistsResponse getFollowedArtists(@RequestParam(required = false) String after, @RequestParam(required = false) Integer limit) {
        return userSpotifyService.getFollowedArtists(after, limit);
    }
}
