package com.northcoders.jv_record_shop.controller;

import com.northcoders.jv_record_shop.dto.response.SongResponseDTO;
import com.northcoders.jv_record_shop.model.Song;
import com.northcoders.jv_record_shop.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/song")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping
    public ResponseEntity<List<SongResponseDTO>> getAllSongs() {
        List<Song> songs = songService.getAllSongs();
        List<SongResponseDTO> songDTOList = songs.stream().map(SongResponseDTO::new).toList();
        return new ResponseEntity<>(songDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongResponseDTO> getSongById(@PathVariable(name = "id") Long id) {
        Song song = songService.getSongById(id);
        if (song == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new SongResponseDTO(song), HttpStatus.OK);
    }

}
