package com.northcoders.jv_record_shop.service.impl;

import com.northcoders.jv_record_shop.dto.request.CreateAlbumRequestDTO;
import com.northcoders.jv_record_shop.dto.request.CreateSongRequestDTO;
import com.northcoders.jv_record_shop.exception.ArtistsNotFoundException;
import com.northcoders.jv_record_shop.model.Album;
import com.northcoders.jv_record_shop.model.Artists;
import com.northcoders.jv_record_shop.model.Song;
import com.northcoders.jv_record_shop.repository.AlbumRepository;
import com.northcoders.jv_record_shop.service.AlbumService;
import com.northcoders.jv_record_shop.service.ArtistsService;
import com.northcoders.jv_record_shop.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistsService artistsService;

    @Autowired
    private SongService songService;

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albumRepository.findAll().forEach(albums::add);
        return albums;
    }

    @Override
    public Album getAlbumById(Long id) {
        return albumRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Album createAlbum(CreateAlbumRequestDTO requestDTO) {
        // TODO: validate releasedDate format


        // create album
        Instant now = Instant.now();
        Album album = new Album(requestDTO);
        album.setCreatedAt(now);
        album.setModifiedAt(now);

        // get artists by ids, throw exception if NOT found
        Set<Long> artistIds = requestDTO.getArtistIds();
        Set<String> songTitles = requestDTO.getSongs().stream()
                .map(CreateSongRequestDTO::getTitle)
                .collect(Collectors.toSet());

        if (artistIds != null) {
            List<Artists> artists = artistsService.getArtistByIds(artistIds);

            Set<Long> diffArtistIds = new HashSet<>(artistIds);
            Set<Long> savedArtistsIds = artists.stream()
                    .map(Artists::getId)
                    .collect(Collectors.toSet());
            diffArtistIds.removeAll(savedArtistsIds);

            if (!diffArtistIds.isEmpty()) {
                throw new ArtistsNotFoundException(diffArtistIds.toString());
            }

            album.setArtists(artists);
        }

        // create song records
        Set<CreateSongRequestDTO> songSet = requestDTO.getSongs();
        if (songSet != null) {

            List<Song> storedSongs = songService.getSongsByTitle(songTitles);

            Set<String> storedSongTitles = storedSongs.stream()
                    .map(Song::getTitle)
                    .collect(Collectors.toSet());

            Set<String> songTitlesClone = new HashSet<>(songTitles);

            songTitlesClone.removeAll(storedSongTitles);

            album.getSongs().addAll(storedSongs);

            if (!songTitlesClone.isEmpty()) {
                // song not stored in db
                List<CreateSongRequestDTO> songDTOs = new ArrayList<>();

                for (CreateSongRequestDTO songRequestDTO : requestDTO.getSongs()) {
                    for (String songTitle : songTitlesClone) {
                        if (songRequestDTO.getTitle().equals(songTitle)) {
                            songDTOs.add(songRequestDTO);
                            break;
                        }
                    }
                }

                List<Song> newSavedSongs = songService.addManySongs(songDTOs);
                album.setSongs(newSavedSongs);
            }

        }

        // save album
        album = albumRepository.save(album);
        return album;
    }
}
