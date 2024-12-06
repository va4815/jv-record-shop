package com.northcoders.jv_record_shop.service.impl;

import com.northcoders.jv_record_shop.model.Artists;
import com.northcoders.jv_record_shop.repository.ArtistsRepository;
import com.northcoders.jv_record_shop.service.ArtistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistsServiceImpl implements ArtistsService {

    @Autowired
    private ArtistsRepository artistsRepository;

    @Override
    public List<Artists> getAllArtists() {
        // TODO: get all artists
        return List.of();
    }

    @Override
    public Artists getArtistById(Long id) {
        // TODO: get artist by id
        return null;
    }
}
