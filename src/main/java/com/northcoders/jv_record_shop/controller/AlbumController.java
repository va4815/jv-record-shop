package com.northcoders.jv_record_shop.controller;

import com.northcoders.jv_record_shop.dto.request.CreateAlbumRequestDTO;
import com.northcoders.jv_record_shop.dto.response.AlbumResponseDTO;
import com.northcoders.jv_record_shop.model.Album;
import com.northcoders.jv_record_shop.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<AlbumResponseDTO>> getAllAlbums() {
        List<Album> albums = albumService.getAllAlbums();
        List<AlbumResponseDTO> albumsDTOList = albums.stream().map(AlbumResponseDTO::new).toList();
        return new ResponseEntity<>(albumsDTOList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlbumResponseDTO> createAlbums(@RequestBody CreateAlbumRequestDTO requestDTO) {
        Album album = albumService.createAlbum(requestDTO);
        return new ResponseEntity<>(new AlbumResponseDTO(album), HttpStatus.CREATED);
    }

}
