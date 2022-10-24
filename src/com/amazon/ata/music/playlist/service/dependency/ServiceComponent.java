package com.amazon.ata.music.playlist.service.dependency;

import com.amazon.ata.music.playlist.service.activity.*;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazonaws.partitions.model.Service;
import dagger.Component;
import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class})
//MARKER: for dagger
public interface ServiceComponent {
    AddSongToPlaylistActivity provideAddSongToPlaylistActivity();
    
    CreatePlaylistActivity provideCreatePlaylistActivity();
    
    GetPlaylistActivity provideGetPlaylistActivity();
    
    GetPlaylistSongsActivity provideGetPlaylistSongsActivity();
    
    UpdatePlaylistActivity provideUpdatePlaylistActivity();
    
//    App provideApp();
}
