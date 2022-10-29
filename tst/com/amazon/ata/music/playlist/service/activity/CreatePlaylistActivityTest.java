package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.music.playlist.service.activity.CreatePlaylistActivity;
import com.amazon.ata.music.playlist.service.models.requests.CreatePlaylistRequest;
import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.exceptions.InvalidAttributeValueException;

import com.amazon.ata.music.playlist.service.models.results.CreatePlaylistResult;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.testng.AssertJUnit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertFalse;

public class CreatePlaylistActivityTest {
    @Mock
    private PlaylistDao playlistDao;

    private CreatePlaylistActivity createPlaylistActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        createPlaylistActivity = new CreatePlaylistActivity(playlistDao);
    }

    @Test
    public void handleRequest_withTags_createsAndSavesPlaylistWithTags() {
        // GIVEN
        String expectedName = "expectedName";
        String expectedCustomerId = "expectedCustomerId";
        int expectedSongCount = 0;
        List<String> expectedTags = Lists.newArrayList("tag");

        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
                                            .withName(expectedName)
                                            .withCustomerId(expectedCustomerId)
                                            .withTags(expectedTags)
                                            .build();

        // WHEN
        CreatePlaylistResult result = createPlaylistActivity.handleRequest(request, null);

        // THEN
        verify(playlistDao).savePlaylist(any(Playlist.class));

        assertNotNull(result.getPlaylist().getId());
        assertEquals(expectedName, result.getPlaylist().getName());
        assertEquals(expectedCustomerId, result.getPlaylist().getCustomerId());
        assertEquals(expectedSongCount, result.getPlaylist().getSongCount());
        assertEquals(expectedTags, result.getPlaylist().getTags());
    }

    @Test
    public void handleRequest_noTags_createsAndSavesPlaylistWithoutTags() {
        // GIVEN
        String expectedName = "expectedName";
        String expectedCustomerId = "expectedCustomerId";
        int expectedSongCount = 0;

        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
                                            .withName(expectedName)
                                            .withCustomerId(expectedCustomerId)
                                            .build();

        // WHEN
        CreatePlaylistResult result = createPlaylistActivity.handleRequest(request, null);

        // THEN
        verify(playlistDao).savePlaylist(any(Playlist.class));

        assertNotNull(result.getPlaylist().getId());
        assertEquals(expectedName, result.getPlaylist().getName());
        assertEquals(expectedCustomerId, result.getPlaylist().getCustomerId());
        assertEquals(expectedSongCount, result.getPlaylist().getSongCount());
        assertNull(result.getPlaylist().getTags());
    }

    @Test
    public void handleRequest_invalidName_throwsInvalidAttributeValueException() {
        // GIVEN
        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
                                            .withName("I'm illegal")
                                            .withCustomerId("customerId")
                                            .build();

        // WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, () -> createPlaylistActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_invalidCustomerId_throwsInvalidAttributeValueException() {
        // GIVEN
        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
                                            .withName("AllOK")
                                            .withCustomerId("Jemma's \"illegal\" customer ID")
                                            .build();

        // WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, () -> createPlaylistActivity.handleRequest(request, null));
    }
    
    @Test
    void testHandleRequest_musicPlaylistServiceUtilsIsValid_doesNotThrowException() {
        // GIVEN
        String       expectedName       = "expectedName";
        String       expectedCustomerId = "expectedCustomerId";
        int          expectedSongCount  = 0;
        List<String> expectedTags       = org.testng.collections.Lists.newArrayList("tag");
        
        CreatePlaylistRequest request =
          CreatePlaylistRequest.builder()
            .withName(expectedName)
            .withCustomerId(expectedCustomerId)
            .withTags(expectedTags)
            .build();
        
        // WHEN
        CreatePlaylistResult result =
          createPlaylistActivity.handleRequest(request, null);
        
        // THEN
        verify(playlistDao).savePlaylist(any(Playlist.class));
        
        AssertJUnit.assertNotNull(result.getPlaylist().getId());
        AssertJUnit.assertEquals(expectedName, result.getPlaylist().getName());
        AssertJUnit.assertEquals(expectedCustomerId, result.getPlaylist().getCustomerId());
        AssertJUnit.assertEquals(expectedSongCount, result.getPlaylist().getSongCount());
        AssertJUnit.assertEquals(expectedTags, result.getPlaylist().getTags());
    }
    
    @Test
    void handleRequest_musicPlaylistServiceNullCustomerId_ThrowsInvalidAttributeException() {
        // GIVEN
        String       expectedName       = "expectedName";
        String       expectedCustomerId = null;
        int          expectedSongCount  = 0;
        List<String> expectedTags       = org.testng.collections.Lists.newArrayList("tag");
        
        CreatePlaylistRequest request =
          CreatePlaylistRequest.builder()
            .withName(expectedName)
            .withCustomerId(null)
            .withTags(expectedTags)
            .build();
        
        // WHEN
        InvalidAttributeValueException exception =
          assertThrows(InvalidAttributeValueException.class,
                       () -> createPlaylistActivity.handleRequest(request, null));
        
        // THEN
        assertFalse(exception.getMessage().isEmpty());
        System.out.println("exception.getMessage() = " + exception.getMessage());
    }
}
