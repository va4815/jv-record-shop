package com.northcoders.jv_record_shop.controller;

import com.northcoders.jv_record_shop.dto.response.ArtistsResponseDTO;
import com.northcoders.jv_record_shop.model.Artists;
import com.northcoders.jv_record_shop.service.ArtistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artists")
public class ArtistsController {

    @Autowired
    private ArtistsService artistsService;

    @GetMapping
    public ResponseEntity<List<ArtistsResponseDTO>> getAllArtists() {
        List<Artists> artists = artistsService.getAllArtists();
        List<ArtistsResponseDTO> artistsDTOList = artists.stream().map(ArtistsResponseDTO::new).toList();
        return new ResponseEntity<>(artistsDTOList, HttpStatus.OK);
    }

}
