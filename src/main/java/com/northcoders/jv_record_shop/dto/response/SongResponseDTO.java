package com.northcoders.jv_record_shop.dto.response;

import com.northcoders.jv_record_shop.model.Song;
import lombok.Data;

import java.time.Duration;

@Data
public class SongResponseDTO {
    private Long id;
    private String title;
    private String writer;
    private long songLength;

    public SongResponseDTO(Song song) {
        if (song != null) {
            this.id = song.getId();
            this.title = song.getTitle();
            this.writer = song.getWriter();
            this.songLength = song.getSongLength().toSeconds();
        }
    }
}
