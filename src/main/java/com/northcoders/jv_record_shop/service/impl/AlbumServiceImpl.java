package com.northcoders.jv_record_shop.service.impl;

import com.northcoders.jv_record_shop.model.Album;
import com.northcoders.jv_record_shop.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Override
    public List<Album> getAllAlbums() {
        // TODO: implement get all albums
        return List.of();
    }
}
