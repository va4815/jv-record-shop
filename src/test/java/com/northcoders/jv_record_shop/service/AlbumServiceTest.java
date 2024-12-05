package com.northcoders.jv_record_shop.service;

import com.northcoders.jv_record_shop.model.Album;
import com.northcoders.jv_record_shop.model.Artists;
import com.northcoders.jv_record_shop.model.Gender;
import com.northcoders.jv_record_shop.model.Genre;
import com.northcoders.jv_record_shop.repository.AlbumRepository;
import com.northcoders.jv_record_shop.service.impl.AlbumServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
public class AlbumServiceTest {

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumServiceImpl albumServiceImpl;

    @Test
    @DisplayName("get all albums")
    void testGetAllAlbums() {

        Artists taylorSwift = Artists.builder()
                .name("Taylor Swift")
                .gender(Gender.F)
                .build();

        Album album1 = Album.builder()
                .name("Speak Now")
                .releasedDate("25-10-2010")
                .genre(Genre.POP)
                .artists(Set.of(taylorSwift))
                .build();

        Album album2 = Album.builder()
                .name("Fearless")
                .releasedDate("11-11-2008")
                .genre(Genre.POP)
                .artists(Set.of(taylorSwift))
                .build();

        List<Album> albumList = new ArrayList<>();
        albumList.add(album1);
        albumList.add(album2);

        when(mockAlbumRepository.findAll()).thenReturn(albumList);

        List<Album> actualResult = albumServiceImpl.getAllAlbums();

        assertThat(actualResult).hasSize(2);
        assertThat(actualResult).isEqualTo(albumList);

    }

    @Test
    @DisplayName("get album by id")
    void testGetAlbumById() {
        Artists taylorSwift = Artists.builder()
                .name("Taylor Swift")
                .gender(Gender.F)
                .build();

        Album speakNow = Album.builder()
                .name("Speak Now")
                .releasedDate("25-10-2010")
                .genre(Genre.POP)
                .artists(Set.of(taylorSwift))
                .build();

        when(mockAlbumRepository.save(Mockito.any(Album.class))).thenReturn(speakNow);

        Album result = albumServiceImpl.getAlbumById(1L);

        assertThat(result).isEqualTo(speakNow);

    }

}
