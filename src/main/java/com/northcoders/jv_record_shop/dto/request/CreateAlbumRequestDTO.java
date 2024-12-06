package com.northcoders.jv_record_shop.dto.request;

import com.northcoders.jv_record_shop.model.Genre;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class CreateAlbumRequestDTO {
    private String name;
    private String releasedDate;
    private Genre genre;
    private Set<Long> artistIds = new HashSet<>();

}
