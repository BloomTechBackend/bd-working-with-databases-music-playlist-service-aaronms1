package com.amazon.ata.music.playlist.service.lambda;

import com.amazon.ata.music.playlist.service.dependency.*;
import com.amazon.ata.music.playlist.service.models.requests.AddSongToPlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.AddSongToPlaylistResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.inject.Inject;

public class AddSongToPlaylistActivityProvider implements RequestHandler<AddSongToPlaylistRequest, AddSongToPlaylistResult> {

    private static App app;
    @Inject
    //MARKER: for dagger
    public AddSongToPlaylistActivityProvider() {

    }

    @Override
    public AddSongToPlaylistResult handleRequest(final AddSongToPlaylistRequest addSongToPlaylistRequest, Context context) {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger.provideAddSongToPlaylistActivity().handleRequest(addSongToPlaylistRequest, context);
//        app = dagger.provideApp();
        
        return getApp().provideAddSongToPlaylistActivity().handleRequest(addSongToPlaylistRequest, context);
      
    }

    private App getApp() {
        if (app == null) {
            app = new App();
        }

        return app;
    }
}
