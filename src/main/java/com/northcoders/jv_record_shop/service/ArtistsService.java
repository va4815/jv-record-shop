package com.northcoders.jv_record_shop.service;

import com.northcoders.jv_record_shop.dto.request.CreateArtistsRequestDTO;
import com.northcoders.jv_record_shop.model.Artists;

import java.util.Collection;
import java.util.List;

public interface ArtistsService {
    List<Artists> getAllArtists();
    Artists getArtistById(Long id);
    List<Artists> getArtistByIds(Collection<Long> ids);
    Artists createArtist(CreateArtistsRequestDTO requestDTO);
}
