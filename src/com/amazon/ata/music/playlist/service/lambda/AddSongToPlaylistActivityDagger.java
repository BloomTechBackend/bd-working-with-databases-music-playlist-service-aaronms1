package com.amazon.ata.music.playlist.service.lambda;

import com.amazon.ata.music.playlist.service.dependency.DaggerServiceComponent;
import com.amazon.ata.music.playlist.service.dependency.ServiceComponent;
import com.amazon.ata.music.playlist.service.models.requests.AddSongToPlaylistRequest;

import com.amazon.ata.music.playlist.service.models.results.AddSongToPlaylistResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddSongToPlaylistActivityDagger implements RequestHandler<AddSongToPlaylistRequest, AddSongToPlaylistResult> {

    private static final ServiceComponent dagger = DaggerServiceComponent.create();

    public AddSongToPlaylistActivityDagger() {

    }

    @Override
    public AddSongToPlaylistResult handleRequest(final AddSongToPlaylistRequest addSongToPlaylistRequest, Context context) {
        return dagger.provideAddSongToPlaylistActivity().handleRequest(addSongToPlaylistRequest, context);
    }
}
