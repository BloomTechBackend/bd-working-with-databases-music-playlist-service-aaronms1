package com.amazon.ata.music.playlist.service.dynamodb;

import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.exceptions.PlaylistNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PlaylistDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    private PlaylistDao playlistDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
        playlistDao = new PlaylistDao(dynamoDBMapper);
    }

    @Test
    public void getPlaylist_withPlaylistId_callsMapperWithPartitionKey() {
        // GIVEN
        String playlistId = "playlistId";
        when(dynamoDBMapper.load(Playlist.class, playlistId)).thenReturn(new Playlist());

        // WHEN
        Playlist playlist = playlistDao.getPlaylist(playlistId);

        // THEN
        assertNotNull(playlist);
        verify(dynamoDBMapper).load(Playlist.class, playlistId);
    }

    @Test
    public void getPlaylist_playlistIdNotFound_throwsPlaylistNotFoundException() {
        // GIVEN
        String nonexistentPlaylistId = "NotReal";
        when(dynamoDBMapper.load(Playlist.class, nonexistentPlaylistId)).thenReturn(null);

        // WHEN + THEN
        assertThrows(PlaylistNotFoundException.class, () -> playlistDao.getPlaylist(nonexistentPlaylistId));
    }

    @Test
    public void savePlaylist_callsMapperWithPlaylist() {
        // GIVEN
        Playlist playlist = new Playlist();

        // WHEN
        Playlist result = playlistDao.savePlaylist(playlist);

        // THEN
        verify(dynamoDBMapper).save(playlist);
        assertEquals(playlist, result);
    }
}
