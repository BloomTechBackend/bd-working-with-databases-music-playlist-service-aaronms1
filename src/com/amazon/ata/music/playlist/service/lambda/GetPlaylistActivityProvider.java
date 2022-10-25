package com.amazon.ata.music.playlist.service.lambda;

//import com.amazon.ata.music.playlist.service.dependency.App;
import com.amazon.ata.music.playlist.service.dependency.DaggerServiceComponent;
import com.amazon.ata.music.playlist.service.dependency.ServiceComponent;
import com.amazon.ata.music.playlist.service.models.requests.GetPlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.GetPlaylistResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.inject.Inject;

public class GetPlaylistActivityProvider implements RequestHandler<GetPlaylistRequest, GetPlaylistResult> {

    //private static App app;
    @Inject
    //MARKER: for dagger
    public GetPlaylistActivityProvider() {

    }

    @Override
    public GetPlaylistResult handleRequest(final GetPlaylistRequest getPlaylistRequest, Context context) {
        return getServiceComponent().provideGetPlaylistActivity().handleRequest(getPlaylistRequest, context);
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
