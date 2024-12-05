package com.northcoders.jv_record_shop.controller;

import com.northcoders.jv_record_shop.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/song")
public class SongController {

    @Autowired
    private SongService service;

}
