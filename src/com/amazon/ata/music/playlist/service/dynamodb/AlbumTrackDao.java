package com.amazon.ata.music.playlist.service.dynamodb;

import com.amazon.ata.music.playlist.service.dynamodb.models.AlbumTrack;

import com.amazon.ata.music.playlist.service.exceptions.AlbumTrackNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;

/**
 * Accesses data for an album using {@link AlbumTrack} to represent the model in DynamoDB.
 */
public class AlbumTrackDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates an AlbumTrackDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the album_track table
     */
    @Inject
    //MARKER:for dagger
    public AlbumTrackDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }
    
    //MARKER:MT4
    public AlbumTrack getAlbumTrack(String asin, int trackNumber) {
        AlbumTrack albumTrack =
          dynamoDbMapper.load(AlbumTrack.class, asin, trackNumber);
        if (null == albumTrack) {
            //MARKER:exception
            throw new AlbumTrackNotFoundException(
              String.format(
                "Could not find AlbumTrack with ASIN:-->\n '%s'\n and track " +
                  "number-->\n %d", asin, trackNumber));
        }
        return albumTrack;
    }
}
