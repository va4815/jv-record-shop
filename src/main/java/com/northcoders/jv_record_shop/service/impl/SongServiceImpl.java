package com.northcoders.jv_record_shop.service.impl;

import com.northcoders.jv_record_shop.dto.request.CreateSongRequestDTO;
import com.northcoders.jv_record_shop.dto.request.UpdateSongRequestDTO;
import com.northcoders.jv_record_shop.exception.SongNotFoundException;
import com.northcoders.jv_record_shop.model.Song;
import com.northcoders.jv_record_shop.repository.SongRepository;
import com.northcoders.jv_record_shop.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Override
    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        songRepository.findAll().forEach(songs::add);
        return songs;
    }

    @Override
    public Song getSongById(Long id) {
        return songRepository.findById(id).orElse(null);
    }

    @Override
    public Song addSong(CreateSongRequestDTO requestDTO) {
        Song song = new Song(requestDTO);
        return songRepository.save(song);
    }

    @Override
    public Song updateSong(UpdateSongRequestDTO requestDTO) {
        Song song = getSongById(requestDTO.getId());

        if (song == null) {
            throw new SongNotFoundException(requestDTO.getId().toString());
        }

        song.setTitle(requestDTO.getTitle());
        song.setWriter(requestDTO.getWriter());
        song.setSongLength(requestDTO.getSongLength());

        return songRepository.save(song);
    }

    @Override
    public List<Song> addManySongs(List<CreateSongRequestDTO> requestDTOS) {
        List<Song> songRequestDTOs = requestDTOS.stream()
                .map(Song::new)
                .toList();

        List<Song> songs = new ArrayList<>();

        Iterable<Song> songIterable = songRepository.saveAll(songRequestDTOs);
        songIterable.forEach(songs::add);

        return songs;
    }

    @Override
    public List<Song> getSongsByTitle(Set<String> titles) {
        return songRepository.findAllByTitleIn(titles);
    }

    @Override
    public boolean deleteSongById(Long id) {
        Song song = getSongById(id);
        if (song != null) {
            songRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
