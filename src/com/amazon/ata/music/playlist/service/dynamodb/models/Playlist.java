package com.amazon.ata.music.playlist.service.dynamodb.models;

import com.amazon.ata.music.playlist.service.converters.AlbumTrackLinkedListConverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import java.util.List;
import java.util.Set;

/**
 * Represents a record in the playlists table.
 */
@DynamoDBTable(tableName = "playlists")
public class Playlist {
    @DynamoDBHashKey
    private String           id;
    private String           name;
    private Integer          songCount;
    private Set<String>      tags;
    private List<AlbumTrack> songList;
    private String           customerId;
    
    
    public String getName() {
        return name;
    }
    
    public Playlist setName(String name) {
        this.name = name;
        return this;
    }
    
    public Integer getSongCount() {
        return songCount;
    }
    
    public Playlist setSongCount(Integer songCount) {
        this.songCount = songCount;
        return this;
    }
    
    public Set<String> getTags() {
        return tags;
    }
    
    public Playlist setTags(Set<String> tags) {
        this.tags = tags;
        return this;
    }
    
    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // PARTICIPANTS: You do not need to modify the songList getters/setters or annotations
    @DynamoDBTypeConverted(converter = AlbumTrackLinkedListConverter.class)
    @DynamoDBAttribute(attributeName = "songList")
    public List<AlbumTrack> getSongList() {
        return songList;
    }

    public void setSongList(List<AlbumTrack> songList) {
        this.songList = songList;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public String getCustomerId() {
        return customerId;
        
    }
}
