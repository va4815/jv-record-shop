package com.northcoders.jv_record_shop.service.impl;

import com.northcoders.jv_record_shop.dto.request.CreateArtistsRequestDTO;
import com.northcoders.jv_record_shop.dto.request.UpdateArtistsRequestDTO;
import com.northcoders.jv_record_shop.exception.ArtistNotFoundException;
import com.northcoders.jv_record_shop.exception.ArtistsInUseException;
import com.northcoders.jv_record_shop.model.Album;
import com.northcoders.jv_record_shop.model.Artists;
import com.northcoders.jv_record_shop.repository.ArtistsRepository;
import com.northcoders.jv_record_shop.service.AlbumService;
import com.northcoders.jv_record_shop.service.ArtistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ArtistsServiceImpl implements ArtistsService {

    @Autowired
    private ArtistsRepository artistsRepository;

    @Autowired
    @Lazy
    private AlbumService albumService;

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

    @Override
    public Artists updateArtist(UpdateArtistsRequestDTO requestDTO) {
        Artists artist = getArtistById(requestDTO.getId());

        if (artist == null) {
            throw new ArtistNotFoundException(requestDTO.getId().toString());
        }

        artist.setName(requestDTO.getName());
        artist.setGender(requestDTO.getGender());

        return artistsRepository.save(artist);
    }

    @Override
    public boolean deleteArtistById(Long id) {
        Artists artist = getArtistById(id);
        if (artist != null) {
            List<Album> albums = albumService.findAllByArtists(artist);

            if (albums != null && !albums.isEmpty()) {
                throw new ArtistsInUseException(String.valueOf(id));
            }

            artistsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
