package com.northcoders.jv_record_shop.service.impl;

import com.northcoders.jv_record_shop.dto.request.CreateSongRequestDTO;
import com.northcoders.jv_record_shop.model.Song;
import com.northcoders.jv_record_shop.repository.SongRepository;
import com.northcoders.jv_record_shop.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
}
