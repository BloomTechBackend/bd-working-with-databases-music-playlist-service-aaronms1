package com.amazon.ata.music.playlist.service.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

/**
 * Represents a record in the album_tracks table.
 */
@DynamoDBTable(tableName = "album_tracks")
public class AlbumTrack {
    @DynamoDBHashKey
    private  String asin;
    @DynamoDBRangeKey
    private Integer trackNumber;
    private DynamoDBMapper dynamoDbMapper;
    private String albumName;
    private String songTitle;
  
    
    
}
