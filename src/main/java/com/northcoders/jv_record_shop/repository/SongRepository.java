package com.northcoders.jv_record_shop.repository;

import com.northcoders.jv_record_shop.model.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
}
