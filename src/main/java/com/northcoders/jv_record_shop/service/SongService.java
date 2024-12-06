package com.northcoders.jv_record_shop.service;

import com.northcoders.jv_record_shop.dto.request.CreateSongRequestDTO;
import com.northcoders.jv_record_shop.model.Song;

import java.util.List;
import java.util.Set;

public interface SongService {
    List<Song> getAllSongs();
    Song getSongById(Long id);
    Song addSong(CreateSongRequestDTO requestDTO);
    void addManySongs(List<CreateSongRequestDTO> requestDTOS);
    List<Song> getSongsByTitle(Set<String> titles);
}
