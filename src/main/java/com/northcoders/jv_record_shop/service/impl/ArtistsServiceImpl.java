package com.northcoders.jv_record_shop.service.impl;

import com.northcoders.jv_record_shop.dto.request.CreateArtistsRequestDTO;
import com.northcoders.jv_record_shop.model.Artists;
import com.northcoders.jv_record_shop.repository.ArtistsRepository;
import com.northcoders.jv_record_shop.service.ArtistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ArtistsServiceImpl implements ArtistsService {

    @Autowired
    private ArtistsRepository artistsRepository;

    @Override
    public List<Artists> getAllArtists() {
        List<Artists> artists = new ArrayList<>();
        artistsRepository.findAll().forEach(artists::add);
        return artists;
    }

    @Override
    public Artists getArtistById(Long id) {
        return artistsRepository.findById(id).orElse(null);
    }

    @Override
    public List<Artists> getArtistByIds(Collection<Long> ids) {
        return artistsRepository.findAllArtistsByIdIn(ids);
    }

    @Override
    public Artists createArtist(CreateArtistsRequestDTO requestDTO) {
        Artists artist = new Artists(requestDTO);
        return artistsRepository.save(artist);
    }
}
