package com.northcoders.jv_record_shop.service;

import com.northcoders.jv_record_shop.repository.AlbumRepository;
import com.northcoders.jv_record_shop.service.impl.AlbumServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AlbumServiceTest {

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumServiceImpl albumServiceImpl;

}
