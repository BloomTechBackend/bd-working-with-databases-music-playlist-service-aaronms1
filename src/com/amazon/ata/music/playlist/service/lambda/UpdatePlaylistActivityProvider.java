package com.amazon.ata.music.playlist.service.lambda;

//import com.amazon.ata.music.playlist.service.dependency.App;
import com.amazon.ata.music.playlist.service.dependency.DaggerServiceComponent;
import com.amazon.ata.music.playlist.service.dependency.ServiceComponent;
import com.amazon.ata.music.playlist.service.models.requests.UpdatePlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.UpdatePlaylistResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.inject.Inject;

public class UpdatePlaylistActivityProvider implements RequestHandler<UpdatePlaylistRequest, UpdatePlaylistResult> {

   // private static App app;
    @Inject
    //MARKER: for dagger
    public UpdatePlaylistActivityProvider() {

    }

    @Override
    public UpdatePlaylistResult handleRequest(final UpdatePlaylistRequest updatePlaylistRequest, Context context) {
        return getServiceComponent().provideUpdatePlaylistActivity().handleRequest(updatePlaylistRequest, context);
    }
    
    public ServiceComponent getServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }

//    private App getApp() {
//        if (app == null) {
//            app = new App();
//        }
//
//        return app;
//    }
}
