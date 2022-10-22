package com.amazon.ata.music.playlist.service.converters;

import com.amazon.ata.music.playlist.service.models.PlaylistModel;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;

import java.util.ArrayList;

public class ModelConverter {
    /**
     * Converts a provided {@link Playlist} into a {@link PlaylistModel} representation.
     * @param playlist the playlist to convert
     * @return the converted playlist
     */
    public PlaylistModel toPlaylistModel(Playlist playlist) {
        PlaylistModel playlistModel = new PlaylistModel();
        playlistModel.setId(playlist.getId());
        playlistModel.setName(playlist.getName());
        playlistModel.setSongCount(playlist.getSongCount());
        playlistModel
          .setTags(playlist.getTags() ==
                     null || playlist.getTags().isEmpty()
                     ? null : new ArrayList<>(playlist.getTags()));
        playlistModel.setSongList(playlist.getSongList());
        
        return PlaylistModel.builder()
                 .withId(playlist.getId())
                    .withName(playlist.getName())
                    .withSongCount(playlist.getSongCount())
                    .withTags(playlist.getTags() ==
                                null || playlist.getTags().isEmpty()
                                ? null : new ArrayList<>(playlist.getTags()))
                    .withSongList(playlist.getSongList())
                 .withCustomerId(playlist.getCustomerId())
                 .build();
        
    }
}
