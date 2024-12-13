package com.northcoders.jv_record_shop.service;

import com.northcoders.jv_record_shop.dto.request.CreateAlbumRequestDTO;
import com.northcoders.jv_record_shop.dto.request.UpdateAlbumRequestDTO;
import com.northcoders.jv_record_shop.model.Album;
import com.northcoders.jv_record_shop.model.Song;

import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbums();
    Album getAlbumById(Long id);
    Album createAlbum(CreateAlbumRequestDTO requestDTO);
    Album updateAlbum(UpdateAlbumRequestDTO requestDTO);
    boolean deleteAlbumById(Long id);
    List<Album> findAllBySongs(Song song);
}
