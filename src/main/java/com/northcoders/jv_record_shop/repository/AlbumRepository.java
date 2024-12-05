package com.northcoders.jv_record_shop.repository;

import com.northcoders.jv_record_shop.model.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {
}
