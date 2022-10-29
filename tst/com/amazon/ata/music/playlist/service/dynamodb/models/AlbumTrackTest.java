package com.amazon.ata.music.playlist.service.dynamodb.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AlbumTrackTest {
    AlbumTrack albumTrack = new AlbumTrack();
    
    @Test
    void testEquals() {
        //GIVEN
        AlbumTrack albumTrack1 = new AlbumTrack();
        albumTrack1.setAsin("asin");
        albumTrack1.setTrackNumber(1);
        albumTrack1.setAlbumName("albumName");
        albumTrack1.setSongTitle("songTitle");
        
        //WHEN
        albumTrack.setAsin("asin");
        albumTrack.setTrackNumber(1);
        albumTrack.setAlbumName("albumName");
        albumTrack.setSongTitle("songTitle");
        
        //THEN
        Assertions.assertEquals(albumTrack1, albumTrack);
        
    }
    
    @Test
    void testHashCode() {
        //GIVEN
        int result = albumTrack.hashCode();
        
        //WHEN
        int actualResult = albumTrack.hashCode();
        
        //THEN
        Assertions.assertEquals(result, actualResult);
    }
    
    @Test
    void testToString() {
        //GIVEN
        String result = albumTrack.toString();

        //WHEN
        String actualResult = albumTrack.toString();
        
        //THEN
        Assertions.assertEquals(result, actualResult);
    }
}
