package com.amazon.ata.music.playlist.service.converters;

import com.amazon.ata.music.playlist.service.dynamodb.models.AlbumTrack;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.models.PlaylistModel;
import com.amazon.ata.music.playlist.service.models.SongModel;

import java.util.ArrayList;
import java.util.List;

public class ModelConverter {
    /**
     * Converts a provided {@link Playlist} into a {@link PlaylistModel} representation.
     * @param playlist the playlist to convert
     * @return the converted playlist
     */
    public PlaylistModel toPlaylistModel(Playlist playlist) {
        List<String> tags = null;
        
        if (playlist.getTags() != null) {
            tags = new ArrayList<>(playlist.getTags());
        }
        
        return PlaylistModel.builder()
                 .withId(playlist.getId())
                 .withName(playlist.getName())
                 .withCustomerId(playlist.getCustomerId())
                 .withSongCount(playlist.getSongCount())
                 .withTags(tags)
                 .build();
    }
    
    /**
     * Converts a provided AlbumTrack into a SongModel representation.
     * @param albumTrack the AlbumTrack to convert to SongModel
     * @return the converted SongModel with fields mapped from albumTrack
     */
    public SongModel toSongModel(AlbumTrack albumTrack) {
        return SongModel.builder()
                 .withAsin(albumTrack.getAsin())
                 .withTrackNumber(albumTrack.getTrackNumber())
                 .withAlbum(albumTrack.getAlbumName())
                 .withTitle(albumTrack.getSongTitle())
                 .build();
    }
    
    /**
     * Converts a list of AlbumTracks to a list of SongModels.
     * @param albumTracks The AlbumTracks to convert to SongModels
     * @return The converted list of SongModels
     */
    public List<SongModel> toSongModelList(List<AlbumTrack> albumTracks) {
        List<SongModel> songModels = new ArrayList<>();
        for (AlbumTrack albumTrack : albumTracks) {
            songModels.add(toSongModel(albumTrack));
        }
        return songModels;
    }
}
