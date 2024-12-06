package com.northcoders.jv_record_shop.repository;

import com.northcoders.jv_record_shop.model.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
    List<Song> findAllByTitleIn(Collection<String> titles);
}
