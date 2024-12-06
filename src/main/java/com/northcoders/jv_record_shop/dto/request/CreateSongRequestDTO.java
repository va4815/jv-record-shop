package com.northcoders.jv_record_shop.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.Duration;

@Data
@Builder
public class CreateSongRequestDTO {
    private String title;
    private String writer;
    private Duration songLength;
}
