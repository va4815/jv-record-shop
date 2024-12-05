package com.northcoders.jv_record_shop.service;

import com.northcoders.jv_record_shop.repository.ArtistsRepository;
import com.northcoders.jv_record_shop.service.impl.ArtistsServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ArtistsServiceTest {

    @Mock
    private ArtistsRepository mockArtistsRepository;

    @InjectMocks
    private ArtistsServiceImpl artistsServiceImpl;

}
