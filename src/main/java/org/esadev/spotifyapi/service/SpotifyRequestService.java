package org.esadev.spotifyapi.service;

import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.specification.PagingCursorbased;

public interface SpotifyRequestService {

    <T extends AbstractModelObject> PagingCursorbased<T> getUsersFollowedArtistsRequest(Integer limit, String after, ModelObjectType type);
}
