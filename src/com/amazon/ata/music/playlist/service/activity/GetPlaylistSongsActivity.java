package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.music.playlist.service.converters.ModelConverter;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.models.SongOrder;
import com.amazon.ata.music.playlist.service.models.requests.GetPlaylistSongsRequest;
import com.amazon.ata.music.playlist.service.models.results.GetPlaylistSongsResult;
import com.amazon.ata.music.playlist.service.models.SongModel;
import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.*;

/**
 * Implementation of the GetPlaylistSongsActivity for the MusicPlaylistService's GetPlaylistSongs API.
 *
 * This API allows the customer to get the list of songs of a saved playlist.
 */
public class GetPlaylistSongsActivity implements RequestHandler<GetPlaylistSongsRequest, GetPlaylistSongsResult> {
    private final Logger log = LogManager.getLogger();
    private final PlaylistDao playlistDao;

    /**
     * Instantiates a new GetPlaylistSongsActivity object.
     *
     * @param playlistDao PlaylistDao to access the playlist table.
     */
    @Inject
    //MARKER: for dagger
    public GetPlaylistSongsActivity(PlaylistDao playlistDao) {
        this.playlistDao = playlistDao;
    }

    /**
     * This method handles the incoming request by retrieving the playlist from the database.
     * <p>
     * It then returns the playlist's song list.
     * <p>
     * If the playlist does not exist, this should throw a PlaylistNotFoundException.
     *
     * @param getPlaylistSongsRequest request object containing the playlist ID
     * @return getPlaylistSongsResult result object containing the playlist's list of API defined {@link SongModel}s
     */
    @Override
    public GetPlaylistSongsResult handleRequest(
      final GetPlaylistSongsRequest getPlaylistSongsRequest, Context context) {
        
        log.info(
          "Received GetPlaylistSongsRequest {}", getPlaylistSongsRequest);
        //MARKER: below for MT4
        SongOrder songOrder =
          computeSongOrder(getPlaylistSongsRequest.getOrder());
    
        Playlist        playlist   =
          playlistDao.getPlaylist(getPlaylistSongsRequest.getId());
        List<SongModel> songModels =
          new ModelConverter().toSongModelList(playlist.getSongList());
    
        if (songOrder.equals(SongOrder.REVERSED)) {
            Collections.reverse(songModels);
        } else if (songOrder.equals(SongOrder.SHUFFLED)) {
            Collections.shuffle(songModels);
        }
        //MARKER: above for MT4
        return GetPlaylistSongsResult.builder()
                .withSongList(songModels)
                .build();       //MARKER: for MT4
    }
    //MARKER: below for MT4
    private SongOrder computeSongOrder(SongOrder songOrder) {
        SongOrder computedSongOrder = songOrder;
    
        if (null == songOrder) {
            computedSongOrder = SongOrder.DEFAULT;
        } else if (!Arrays.asList(SongOrder.values()).contains(songOrder)) {
            //MARKER: for MT4
            throw new IllegalArgumentException(
              String.format("Unrecognized song order: '%s'", songOrder));
        }
        return computedSongOrder;
    }
}
