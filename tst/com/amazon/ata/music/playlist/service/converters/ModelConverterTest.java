package com.amazon.ata.music.playlist.service.converters;

import com.amazon.ata.music.playlist.service.converters.ModelConverter;
import com.amazon.ata.music.playlist.service.dynamodb.models.AlbumTrack;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.helpers.AlbumTrackTestHelper;
import com.amazon.ata.music.playlist.service.models.PlaylistModel;
import com.amazon.ata.music.playlist.service.models.SongModel;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;
import com.amazon.ata.music.playlist.service.helpers.AlbumTrackTestHelper;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ModelConverterTest {
    private ModelConverter modelConverter = new ModelConverter();

    @Test
    void toPlaylistModel_withTags_convertsPlaylist() {
        Playlist playlist = new Playlist();
        playlist.setId("id");
        playlist.setName("name");
        playlist.setCustomerId("customerId");
        playlist.setSongCount(0);
        playlist.setTags(Sets.newHashSet("tag"));

        PlaylistModel playlistModel = modelConverter.toPlaylistModel(playlist);
        assertEquals(playlist.getId(), playlistModel.getId());
        assertEquals(playlist.getName(), playlistModel.getName());
        assertEquals(playlist.getCustomerId(), playlistModel.getCustomerId());
        assertEquals(playlist.getSongCount(), playlistModel.getSongCount());
        assertEquals(Lists.newArrayList(playlist.getTags()), playlistModel.getTags());
    }

    @Test
    void toPlaylistModel_nullTags_convertsPlaylist() {
        Playlist playlist = new Playlist();
        playlist.setId("id");
        playlist.setName("name");
        playlist.setCustomerId("customerId");
        playlist.setSongCount(0);
        playlist.setTags(null);

        PlaylistModel playlistModel = modelConverter.toPlaylistModel(playlist);
        assertEquals(playlist.getId(), playlistModel.getId());
        assertEquals(playlist.getName(), playlistModel.getName());
        assertEquals(playlist.getCustomerId(), playlistModel.getCustomerId());
        assertEquals(playlist.getSongCount(), playlistModel.getSongCount());
        assertNull(playlistModel.getTags());
    }

    @Test
    void toSongModel_withAlbumTrack_convertsToSongModel() {
        // GIVEN
        AlbumTrack albumTrack = AlbumTrackTestHelper.generateAlbumTrack(2);

        // WHEN
        SongModel result = modelConverter.toSongModel(albumTrack);

        // THEN
        AlbumTrackTestHelper.assertAlbumTrackEqualsSongModel(
            albumTrack,
            result,
            String.format("Expected album track %s to match song model %s",
                          albumTrack,
                          result)
        );
    }

    @Test
    void toSongModelList_withAlbumTracks_convertsToSongModelList() {
        // GIVEN
        // list of AlbumTracks
        int numTracks = 4;
        List<AlbumTrack> albumTracks = new LinkedList<>();
        for (int i = 0; i < numTracks; i++) {
            albumTracks.add(AlbumTrackTestHelper.generateAlbumTrack(i));
        }

        // WHEN
        List<SongModel> result = modelConverter.toSongModelList(albumTracks);

        // THEN
        AlbumTrackTestHelper.assertAlbumTracksEqualSongModels(albumTracks, result);
    }
}
