package com.northcoders.jv_record_shop.repository;

import com.northcoders.jv_record_shop.model.Artists;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ArtistsRepository extends CrudRepository<Artists, Long> {
    List<Artists> findAllArtistsByIdIn(Collection<Long> ids);
}
