package com.northcoders.jv_record_shop.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.Duration;

@Data
@Builder
public class UpdateSongRequestDTO {
    private Long id;
    private String title;
    private String writer;
    private Duration songLength;
}
