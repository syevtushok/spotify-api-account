package org.esadev.spotifyapi.exception;

public class SpotifyRequestException extends RuntimeException {
    public SpotifyRequestException(String message, Exception cause) {
        super(message, cause);
    }
}
