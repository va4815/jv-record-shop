package com.northcoders.jv_record_shop.service;

import com.northcoders.jv_record_shop.repository.SongRepository;
import com.northcoders.jv_record_shop.service.impl.SongServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class SongServiceTest {

    @Mock
    private SongRepository mockSongRepository;

    @InjectMocks
    private SongServiceImpl songServiceImpl;
    
}
