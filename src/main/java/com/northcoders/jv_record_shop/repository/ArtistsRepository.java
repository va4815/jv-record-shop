package com.northcoders.jv_record_shop.repository;

import com.northcoders.jv_record_shop.model.Artists;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistsRepository extends CrudRepository<Artists, Long> {
}
