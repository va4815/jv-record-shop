package com.northcoders.jv_record_shop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ArtistsRepository {

    @Autowired
    private ArtistsRepository artistsRepository;

}
