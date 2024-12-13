package com.northcoders.jv_record_shop.repository;

import com.northcoders.jv_record_shop.model.Album;
import com.northcoders.jv_record_shop.model.Artists;
import com.northcoders.jv_record_shop.model.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {
    List<Album> findAllBySongs(Song song);
    List<Album> findAllByArtists(Artists artists);
}
